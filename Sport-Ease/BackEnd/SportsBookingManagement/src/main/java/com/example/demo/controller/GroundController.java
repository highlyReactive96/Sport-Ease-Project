package com.example.demo.controller;

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

import com.example.demo.dto.GroundDto;
import com.example.demo.service.GroundService;

import lombok.RequiredArgsConstructor;

@RestController
	@RequestMapping("/grounds")
	@RequiredArgsConstructor
	public class GroundController {
		@Autowired
	    private  GroundService groundService;
		@Autowired
	    private  ModelMapper modelMapper;

	    @GetMapping
	    public ResponseEntity<List<GroundDto>> getAllGrounds() {
	        List<GroundDto> groundDtos = groundService.getAllGrounds();
	        return ResponseEntity.ok(groundDtos);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<GroundDto> getGroundById(@PathVariable Long id) {
	        GroundDto groundDto = groundService.getGroundById(id);
	        return ResponseEntity.ok(groundDto);
	    }

	    @PostMapping
	    public ResponseEntity<GroundDto> createGround(@RequestBody GroundDto groundDto) {
	        GroundDto savedGroundDto = groundService.createGround(groundDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroundDto);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<GroundDto> updateGround(@PathVariable Long id, @RequestBody GroundDto groundDto) {
	        GroundDto updatedGroundDto = groundService.updateGround(id, groundDto);
	        return ResponseEntity.ok(updatedGroundDto);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteGround(@PathVariable Long id) {
	        groundService.deleteGround(id);
	        return ResponseEntity.noContent().build();
	    }
	    @GetMapping("/sport/{sport}")
	    public ResponseEntity<List<GroundDto>> getGroundsBySport(@PathVariable String sport) {
	        List<GroundDto> groundDtos = groundService.getGroundsBySport(sport);
	        return ResponseEntity.ok(groundDtos);
	    }
	}



