package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BookingDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.Booking;
import com.example.demo.pojo.Player;
import com.example.demo.pojo.TimeSlot;
import com.example.demo.repo.AcademyRepository;
import com.example.demo.repo.BookingRepository;
import com.example.demo.repo.PlayerRepository;
@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
    private  BookingRepository bookingRepository;
	@Autowired
    private  AcademyRepository academyRepository;
	@Autowired
    private  PlayerRepository playerRepository;
	@Autowired
    private  ModelMapper modelMapper;

//    public BookingServiceImpl(BookingRepository bookingRepository, AcademyRepository academyRepository,
//                              PlayerRepository playerRepository, ModelMapper modelMapper) {
//        this.bookingRepository = bookingRepository;
//        this.academyRepository = academyRepository;
//        this.playerRepository = playerRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        Academy academy = academyRepository.findById(bookingDto.getAcademyId())
                .orElseThrow(() -> new ResourceNotFoundException("Academy not found with id: " + bookingDto.getAcademyId()));
        Player player = playerRepository.findById(bookingDto.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + bookingDto.getPlayerId()));

        Booking booking = modelMapper.map(bookingDto, Booking.class);
        booking.setAcademy(academy);
        booking.setPlayer(player);

        return modelMapper.map(bookingRepository.save(booking), BookingDto.class);
    }

    @Override
    public BookingDto getBookingByCode(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with code: " + bookingCode));

        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    public BookingDto updateBooking(Long id, BookingDto bookingDto) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        Academy academy = academyRepository.findById(bookingDto.getAcademyId())
                .orElseThrow(() -> new ResourceNotFoundException("Academy not found with id: " + bookingDto.getAcademyId()));
        Player player = playerRepository.findById(bookingDto.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + bookingDto.getPlayerId()));

        Booking booking = modelMapper.map(bookingDto, Booking.class);
        booking.setId(existingBooking.getId());
        booking.setAcademy(academy);
        booking.setPlayer(player);

        return modelMapper.map(bookingRepository.save(booking), BookingDto.class);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByAcademy(Long academyId) {
        List<Booking> bookings = bookingRepository.findByAcademyId(academyId);

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByPlayer(Long playerId) {
        List<Booking> bookings = bookingRepository.findByPlayerId(playerId);

        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public List<BookingDto> getBookingsByAcademyCity(String city) {
		  List<Booking> bookings = bookingRepository.findByAcademyCity(city);
		    return bookings.stream()
		            .map(booking -> modelMapper.map(booking, BookingDto.class))
		            .collect(Collectors.toList());
	}

//	@Override
//	public List<BookingDto> getBookingsByAcademyName(String name) {
//		
//		return null;
//	}

	@Override
	public List<BookingDto> getBookingsByAcademyCityAndTimeSlot(String city, TimeSlot timeSlot) {
		 List<Booking> bookings = bookingRepository.findByAcademyCityAndSlot(city, timeSlot);

		    return bookings.stream()
		            .map(booking -> modelMapper.map(booking, BookingDto.class))
		            .collect(Collectors.toList());
	}

	@Override
	public List<BookingDto> getBookingsByAcademySports(String sports) {
	    List<Booking> bookings = bookingRepository.findBookingsByAcademySportList(sports);
	    List<BookingDto> bookingDtos = new ArrayList<>();
	    for (Booking booking : bookings) {
	        BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
	        bookingDtos.add(bookingDto);
	    }
	    return bookingDtos;
	}


	@Override
	public List<BookingDto> getBookingsByAcademyName(String name) {
		List<Booking> bookings = bookingRepository.findByAcademyAcademyName(name);
	    List<BookingDto> bookingDtos = bookings.stream()
	            .map(booking -> modelMapper.map(booking, BookingDto.class))
	            .collect(Collectors.toList());
	    return bookingDtos;

	}
}
