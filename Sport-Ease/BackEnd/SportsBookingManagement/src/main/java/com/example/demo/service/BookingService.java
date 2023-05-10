package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BookingDto;
import com.example.demo.pojo.TimeSlot;

public interface BookingService {
	BookingDto getBookingByCode(String bookingCode);

	BookingDto updateBooking(Long id, BookingDto bookingDto);

	void deleteBooking(Long id);

	List<BookingDto> getAllBookings();

	List<BookingDto> getBookingsByAcademy(Long academyId);

	List<BookingDto> getBookingsByPlayer(Long playerId);

	List<BookingDto> getBookingsByAcademyCity(String city);

	List<BookingDto> getBookingsByAcademyCityAndTimeSlot(String city, TimeSlot timeSlot);

	List<BookingDto> getBookingsByAcademySports(String sports);

	List<BookingDto> getBookingsByAcademyName(String name);

	BookingDto addBooking(BookingDto bookingDto);


}
