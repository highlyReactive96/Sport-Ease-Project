package com.example.demo.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Booking;
import com.example.demo.pojo.TimeSlot;
import com.example.demo.pojo.Academy;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingCode(String bookingCode);
    List<Booking> findAllByAcademyId(Long academyId);
    List<Booking> findAllByPlayerId(Long playerId);
    List<Booking> findAllByAcademyIdAndSlotSlotDateTimeStartBetween(Long academyId, Date start, Date end);
	List<Booking> findByPlayerId(Long playerId);
	List<Booking> findByAcademyId(Long academyId);
	List<Booking> findByAcademyCity(String city);
	//List<Booking> findByAcademyCityAndTimeSlot(String city, TimeSlot timeSlot);
	List<Booking> findBookingsByAcademySportList(String sports);
	//List<Booking> findByAcademySports(String sports);
	//List<Booking> findByAcademyCityAndTimeSlot(String city, TimeSlot timeSlot);
	List<Booking> findByAcademyAcademyName(String academyName);
	List<Booking> findByAcademyCityAndSlot(String city, TimeSlot timeSlot);
}

