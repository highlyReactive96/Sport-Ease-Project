package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojo.Academy;
import com.example.demo.pojo.SportsType;

public interface AcademyService {
    List<Academy> findAll();
    Academy findById(Long id);
    List<Academy> findBySports(SportsType sports);
    List<Academy> findByAcademyName(String academyName);
    List<Academy> findByCity(String city);
    List<Academy> findByCityAndSports(String city, SportsType sports);
    List<Academy> getAllAcademy();
    
    List<String> finddistinctCities();
    
}
