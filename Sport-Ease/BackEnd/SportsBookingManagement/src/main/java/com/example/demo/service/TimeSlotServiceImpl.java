package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.TimeSlotDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.TimeSlot;
import com.example.demo.repo.TimeSlotRepository;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TimeSlotDto> getTimeSlotsByGroundId(Long groundId) {
        List<TimeSlot> timeSlots = timeSlotRepository.findByGroundId(groundId);
        return timeSlots.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotDto> getTimeSlotsByDate(LocalDate date) {
        List<TimeSlot> timeSlots = timeSlotRepository.findByDate(date);
        return timeSlots.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotDto> getAvailableTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotRepository.findByIsAvailable(true);
        return timeSlots.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlotDto createTimeSlot(TimeSlotDto timeSlotDTO) {
        TimeSlot timeSlot = convertToEntity(timeSlotDTO);
        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
        return convertToDto(savedTimeSlot);
    }

    @Override
    public void deleteTimeSlot(Long timeSlotId) {
        timeSlotRepository.deleteById(timeSlotId);
    }

    @Override
    public TimeSlotDto updateTimeSlot(Long id, TimeSlotDto timeSlotDto) {
        TimeSlot existingTimeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Time slot not found with id " + id));
        TimeSlot updatedTimeSlot = convertToEntity(timeSlotDto);
        existingTimeSlot.setDate(updatedTimeSlot.getDate());
        existingTimeSlot.setSlotDateTimeStart(updatedTimeSlot.getSlotDateTimeStart());
        existingTimeSlot.setSlotDateTimeEnd(updatedTimeSlot.getSlotDateTimeEnd());
        existingTimeSlot.setRentAmount(updatedTimeSlot.getRentAmount());
        existingTimeSlot.setIsAvailable(updatedTimeSlot.getIsAvailable());
        existingTimeSlot.setGround(updatedTimeSlot.getGround());
        TimeSlot savedTimeSlot = timeSlotRepository.save(existingTimeSlot);
        return convertToDto(savedTimeSlot);
    }

    @Override
    public List<TimeSlotDto> getTimeSlotsByIsAvailable(Boolean isAvailable) {
        List<TimeSlot> timeSlots = timeSlotRepository.findByIsAvailable(isAvailable);
        return timeSlots.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TimeSlotDto convertToDto(TimeSlot timeSlot) {
        return modelMapper.map(timeSlot, TimeSlotDto.class);
    }

    private TimeSlot convertToEntity(TimeSlotDto timeSlotDto) {
        return modelMapper.map(timeSlotDto, TimeSlot.class);
    }
}


