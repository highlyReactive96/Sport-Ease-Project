package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AcademyDto;
import com.example.demo.dto.AcademyOwnerDto;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.AcademyOwner;

public interface AcademyOwnerService {
    List<AcademyOwnerDto> getAllAcademyOwners();
    AcademyOwnerDto getAcademyOwnerById(Long id);
    AcademyOwnerDto createAcademyOwner(AcademyOwnerDto academyOwnerDto);
    AcademyOwnerDto updateAcademyOwner(Long id, AcademyOwnerDto academyOwnerDto);
    void deleteAcademyOwner(Long id);
    String addAcademyToOwner(String email, AcademyDto academy);
	
}
