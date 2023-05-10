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

import com.example.demo.dto.AcademyOwnerDto;
import com.example.demo.service.AcademyOwnerService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AcademyOwnerService academyOwnerService;
	@Autowired
	  private  ModelMapper modelMapper;
	
	  @GetMapping("/ownerlist")
	    public ResponseEntity<List<AcademyOwnerDto>> getAllAcademyOwners() {
	        List<AcademyOwnerDto> academyOwnerDtos = academyOwnerService.getAllAcademyOwners();
	        return ResponseEntity.ok(academyOwnerDtos);
	    }

	  @PostMapping("/addOwner")
	    public ResponseEntity<AcademyOwnerDto> addAcademyOwner(@RequestBody AcademyOwnerDto academyOwnerDto) {
	        AcademyOwnerDto createdAcademyOwnerDto = academyOwnerService.createAcademyOwner(academyOwnerDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdAcademyOwnerDto);
	    }
	  
	  @PutMapping("/{id}")
	    public ResponseEntity<AcademyOwnerDto> updateAcademyOwner(@PathVariable Long id, @RequestBody AcademyOwnerDto academyOwnerDto) {
	        AcademyOwnerDto updatedAcademyOwnerDto = academyOwnerService.updateAcademyOwner(id, academyOwnerDto);
	        if (updatedAcademyOwnerDto == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(updatedAcademyOwnerDto);
	    }
	  
	  @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteAcademyOwner(@PathVariable Long id) {
	        academyOwnerService.deleteAcademyOwner(id);
	        return ResponseEntity.noContent().build();
	    }
	  
	  @GetMapping("/{id}")
	    public ResponseEntity<AcademyOwnerDto> findAcademyOwnerById(@PathVariable Long id) {
	        AcademyOwnerDto academyOwnerDto = academyOwnerService.getAcademyOwnerById(id);
	        if (academyOwnerDto == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(academyOwnerDto);
	    }
	  
}

