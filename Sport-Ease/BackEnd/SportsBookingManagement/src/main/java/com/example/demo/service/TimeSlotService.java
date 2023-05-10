package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.TimeSlotDto;

public interface TimeSlotService {
	    List<TimeSlotDto> getTimeSlotsByGroundId(Long groundId);
	    List<TimeSlotDto> getTimeSlotsByDate(LocalDate date);
	    List<TimeSlotDto> getAvailableTimeSlots();
	    TimeSlotDto createTimeSlot(TimeSlotDto timeSlotDTO);
	    void deleteTimeSlot(Long timeSlotId);
	    TimeSlotDto updateTimeSlot(Long id, TimeSlotDto timeSlotDto);
	    List<TimeSlotDto> getTimeSlotsByIsAvailable(Boolean isAvailable);
	}

