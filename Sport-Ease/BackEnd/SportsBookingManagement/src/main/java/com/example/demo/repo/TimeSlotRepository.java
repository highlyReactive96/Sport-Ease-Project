package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.TimeSlot;
@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
	
	    List<TimeSlot> findByGroundId(Long groundId);
	    List<TimeSlot> findByDate(LocalDate date);
	    List<TimeSlot> findByIsAvailable(Boolean isAvailable);
}
