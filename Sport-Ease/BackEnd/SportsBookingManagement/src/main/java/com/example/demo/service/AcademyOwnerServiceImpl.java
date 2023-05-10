package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AcademyDto;
import com.example.demo.dto.AcademyOwnerDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.AcademyOwner;
import com.example.demo.pojo.Role;
import com.example.demo.repo.AcademyOwnerRepository;
import com.example.demo.repo.AcademyRepository;

@Service
@Transactional
public class AcademyOwnerServiceImpl implements AcademyOwnerService {
    
    @Autowired
    private AcademyOwnerRepository academyOwnerRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AcademyRepository academyRepository;
    @Override
    public List<AcademyOwnerDto> getAllAcademyOwners() {
        List<AcademyOwner> academyOwners = academyOwnerRepository.findAll();
        return academyOwners.stream()
                .map(academyOwner -> modelMapper.map(academyOwner, AcademyOwnerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AcademyOwnerDto getAcademyOwnerById(Long id) {
        AcademyOwner academyOwner = academyOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academy Owner with id " + id + " not found"));
        return modelMapper.map(academyOwner, AcademyOwnerDto.class);
    }

    @Override
    public AcademyOwnerDto createAcademyOwner(AcademyOwnerDto academyOwnerDto) {
        AcademyOwner academyOwner = modelMapper.map(academyOwnerDto, AcademyOwner.class);
        academyOwner.setRole(Role.ROLE_ACADEMY);
        academyOwner = academyOwnerRepository.save(academyOwner);
        return modelMapper.map(academyOwner, AcademyOwnerDto.class);
    }

    @Override
    public AcademyOwnerDto updateAcademyOwner(Long id, AcademyOwnerDto academyOwnerDto) {
        AcademyOwner academyOwner = academyOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academy Owner with id " + id + " not found"));
        modelMapper.map(academyOwnerDto, academyOwner);
        academyOwner = academyOwnerRepository.save(academyOwner);
        return modelMapper.map(academyOwner, AcademyOwnerDto.class);
    }

    @Override
    public void deleteAcademyOwner(Long id) {
        AcademyOwner academyOwner = academyOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Academy Owner with id " + id + " not found"));
        academyOwnerRepository.delete(academyOwner);
    }

	@Override
	public String addAcademyToOwner(String email, AcademyDto academyDto) {
		Optional<AcademyOwner> ownerOptional = academyOwnerRepository.findByEmail(email);
		System.out.println(ownerOptional);
		try {
        if (ownerOptional.isPresent()) {
            AcademyOwner owner = ownerOptional.get();
            Academy academy = modelMapper.map(academyDto, Academy.class);
            academy.setOwner(owner);
            owner.getAcademyList().add(academy);
            academyRepository.save(academy);
            academyOwnerRepository.save(owner);
            return "OK";
        }else {
        	throw new ResourceNotFoundException("Owner not found with email: " + email);
        }
		}catch (Exception e){
			return e.getMessage();
        }
    }
		
	}

