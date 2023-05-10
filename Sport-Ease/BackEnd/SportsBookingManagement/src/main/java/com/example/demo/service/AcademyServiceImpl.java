package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojo.Academy;
import com.example.demo.pojo.SportsType;
//import com.example.demo.repo.AcademyRepo;
import com.example.demo.repo.AcademyRepository;

@Service
@Transactional
public class AcademyServiceImpl implements AcademyService {

	
	@Autowired
	AcademyRepository academyRepository;
	
	
	@Override
	
	public List<Academy> findAll() {
		return academyRepository.findAll();
		
		
	}

	@Override
	public Academy findById(Long id) {
		 return academyRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Academy not found with id " + id));
	}

	@Override
	public List<Academy> findBySports(SportsType sports) {
		
		return academyRepository.findBySportListSports(sports);
	}

	@Override
	public List<Academy> findByAcademyName(String academyName) {
		return academyRepository.findByAcademyNameContainingIgnoreCase(academyName);
	}

	@Override
	public List<Academy> findByCity(String city) {
		return academyRepository.findByCityIgnoreCase(city);
	}

	@Override
	public List<Academy> findByCityAndSports(String city, SportsType sports) {
		return academyRepository.findByCityIgnoreCaseAndSportListSports(city, sports);
	}

	@Override
	public List<Academy> getAllAcademy() {
		academyRepository.findAll().size();
		
		return academyRepository.findAll();
	}

	@Override
	public List<String> finddistinctCities() {
		
		return academyRepository.findDistinctCities();
	}
	
	

	
}
