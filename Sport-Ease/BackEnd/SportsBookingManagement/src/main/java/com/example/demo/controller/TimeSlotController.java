package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TimeSlotDto;
import com.example.demo.service.TimeSlotService;

@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {
    
    @Autowired
    private TimeSlotService timeSlotService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/ground/{groundId}")
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsByGroundId(@PathVariable Long groundId) {
        List<TimeSlotDto> timeSlotDtos = timeSlotService.getTimeSlotsByGroundId(groundId);
        return ResponseEntity.ok(timeSlotDtos);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        List<TimeSlotDto> timeSlotDtos = timeSlotService.getTimeSlotsByDate(date);
        return ResponseEntity.ok(timeSlotDtos);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<TimeSlotDto>> getAvailableTimeSlots() {
        List<TimeSlotDto> timeSlotDtos = timeSlotService.getAvailableTimeSlots();
        return ResponseEntity.ok(timeSlotDtos);
    }
    
    @PostMapping("/")
    public ResponseEntity<TimeSlotDto> createTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
        TimeSlotDto createdTimeSlot = timeSlotService.createTimeSlot(timeSlotDto);
        return ResponseEntity.ok(createdTimeSlot);
    }
    
    @DeleteMapping("/{timeSlotId}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long timeSlotId) {
        timeSlotService.deleteTimeSlot(timeSlotId);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TimeSlotDto> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        TimeSlotDto updatedTimeSlot = timeSlotService.updateTimeSlot(id, timeSlotDto);
        return ResponseEntity.ok(updatedTimeSlot);
    }
    
    @GetMapping("/available/{isAvailable}")
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsByIsAvailable(@PathVariable Boolean isAvailable) {
        List<TimeSlotDto> timeSlotDtos = timeSlotService.getTimeSlotsByIsAvailable(true);
        return ResponseEntity.ok(timeSlotDtos);
    }
}

