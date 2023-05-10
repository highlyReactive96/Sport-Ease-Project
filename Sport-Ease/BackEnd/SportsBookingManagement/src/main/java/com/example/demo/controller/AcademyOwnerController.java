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

import com.example.demo.dto.AcademyDto;
import com.example.demo.dto.AcademyOwnerDto;
import com.example.demo.pojo.Academy;
import com.example.demo.service.AcademyOwnerService;
import com.example.demo.service.AcademyService;

@RestController
@RequestMapping("/academyOwner")
public class AcademyOwnerController {
	
    @Autowired
    private AcademyOwnerService academyOwnerService;
    @Autowired
    private AcademyService academyService;
    @Autowired
    private ModelMapper modelMapper;
    
    
   
    @PostMapping("/addacademy/{email}")
    public String addAcademyToOwner(@PathVariable String email, @RequestBody AcademyDto academy) {
        return academyOwnerService.addAcademyToOwner(email, academy);
    }
    
    
    @Autowired
    public AcademyOwnerController(AcademyOwnerService academyOwnerService, ModelMapper modelMapper) {
        this.academyOwnerService = academyOwnerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<AcademyOwnerDto>> getAllAcademyOwners() {
        List<AcademyOwnerDto> academyOwnerDtos = academyOwnerService.getAllAcademyOwners();
        return ResponseEntity.ok(academyOwnerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademyOwnerDto> getAcademyOwnerById(@PathVariable Long id) {
        AcademyOwnerDto academyOwnerDto = academyOwnerService.getAcademyOwnerById(id);
        if (academyOwnerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(academyOwnerDto);
    }

    @PostMapping
    public ResponseEntity<AcademyOwnerDto> createAcademyOwner(@RequestBody AcademyOwnerDto academyOwnerDto) {
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
}

