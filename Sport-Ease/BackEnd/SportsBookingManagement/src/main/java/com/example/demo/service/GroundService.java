package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.GroundDto;

public interface GroundService {
	    List<GroundDto> getAllGrounds();
	    GroundDto getGroundById(Long id);
	    GroundDto createGround(GroundDto groundDto);
	    GroundDto updateGround(Long id, GroundDto groundDto);
	    void deleteGround(Long id);
	    List<GroundDto> getGroundsBySport(String sport);


}
