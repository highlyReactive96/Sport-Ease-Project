package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.GroundDto;
import com.example.demo.exception.GroundNotFoundException;
import com.example.demo.pojo.Ground;
import com.example.demo.pojo.Sports;
import com.example.demo.repo.GroundRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GroundServiceImpl implements GroundService {
    private final GroundRepository groundRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GroundDto> getAllGrounds() {
        List<Ground> grounds = groundRepository.findAll();
        return grounds.stream()
                .map(ground -> modelMapper.map(ground, GroundDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GroundDto getGroundById(Long id) {
        Ground ground = groundRepository.findById(id)
                .orElseThrow(() -> new GroundNotFoundException(id));
        return modelMapper.map(ground, GroundDto.class);
    }

    @Override
    public GroundDto createGround(GroundDto groundDto) {
        Ground ground = modelMapper.map(groundDto, Ground.class);
        Ground savedGround = groundRepository.save(ground);
        return modelMapper.map(savedGround, GroundDto.class);
    }

    @Override
    public GroundDto updateGround(Long id, GroundDto groundDto) {
        Ground ground = groundRepository.findById(id)
                .orElseThrow(() -> new GroundNotFoundException(id));
        modelMapper.map(groundDto, ground);
        Ground updatedGround = groundRepository.save(ground);
        return modelMapper.map(updatedGround, GroundDto.class);
    }

    @Override
    public void deleteGround(Long id) {
        groundRepository.deleteById(id);
    }
    
//    @Override
//    public List<GroundDto> getGroundsBySport(String sport) {
//        Sports sports = Sports.valueOf(sport);
//        List<Ground> groundList = groundRepository.findBySports(sports);
//        return groundList.stream()
//                .map(ground -> modelMapper.map(ground, GroundDto.class))
//                .collect(Collectors.toList());
//    }
    
//    @Override
//    public List<GroundDto> getGroundsBySport(String sport) {
//        try {
//            Sports sports = Sports.valueOf(sport);
//            List<Ground> groundList = groundRepository.findBySports(sports);
//            return groundList.stream()
//                    .map(ground -> modelMapper.map(ground, GroundDto.class))
//                    .collect(Collectors.toList());
//        } catch (IllegalArgumentException ex) {
//            throw new BadRequestException("Invalid sport: " + sport);
//        }
//    }
    
    @Override
    public List<GroundDto> getGroundsBySport(String sport) {
        Sports sports = Sports.fromString(sport);
        List<Ground> groundList = groundRepository.findBySports(sports);
        return groundList.stream()
                .map(ground -> modelMapper.map(ground, GroundDto.class))
                .collect(Collectors.toList());
    }
}
