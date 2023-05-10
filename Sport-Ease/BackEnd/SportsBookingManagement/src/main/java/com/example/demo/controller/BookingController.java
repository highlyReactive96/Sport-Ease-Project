package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingDto;
import com.example.demo.pojo.TimeSlot;
import com.example.demo.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private  BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto bookingDto) {
        BookingDto createdBooking = bookingService.addBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @GetMapping("/{bookingCode}")
    public ResponseEntity<BookingDto> getBookingByCode(@PathVariable String bookingCode) {
        BookingDto booking = bookingService.getBookingByCode(bookingCode);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        BookingDto updatedBooking = bookingService.updateBooking(id, bookingDto);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/academy/{academyId}")
    public ResponseEntity<List<BookingDto>> getBookingsByAcademy(@PathVariable Long academyId) {
        List<BookingDto> bookings = bookingService.getBookingsByAcademy(academyId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<BookingDto>> getBookingsByPlayer(@PathVariable Long playerId) {
        List<BookingDto> bookings = bookingService.getBookingsByPlayer(playerId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/academy/city/{city}")
    public ResponseEntity<List<BookingDto>> getBookingsByAcademyCity(@PathVariable String city) {
        List<BookingDto> bookings = bookingService.getBookingsByAcademyCity(city);
        return ResponseEntity.ok(bookings);
    }

//    @GetMapping("/academy/city/{city}/time-slot/{timeSlot}")
//    public ResponseEntity<List<BookingDto>> getBookingsByAcademyCityAndTimeSlot(@PathVariable String city, @PathVariable TimeSlot timeSlot) {
//        List<BookingDto> bookings = bookingService.getBookingsByAcademyCityAndTimeSlot(city, timeSlot);
//        return ResponseEntity.ok(bookings);
//    }

//    @GetMapping("/academy/sports/{sports}")
//    public ResponseEntity<List<BookingDto>> getBookingsByAcademySports(@PathVariable String sports) {
//        List<BookingDto> bookings = bookingService.getBookingsByAcademySports(sports);
//        return ResponseEntity.ok(bookings);
//    }

    @GetMapping("/academy/name/{name}")
    public ResponseEntity<List<BookingDto>> getBookingsByAcademyName(@PathVariable String name) {
        List<BookingDto> bookings = bookingService.getBookingsByAcademyName(name);
        return ResponseEntity.ok(bookings);
    }
}
