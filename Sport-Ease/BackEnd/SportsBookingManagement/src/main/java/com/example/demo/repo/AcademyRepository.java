package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Academy;
import com.example.demo.pojo.SportsType;
@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {
    List<Academy> findBySportListSports(SportsType sports);
    List<Academy> findByAcademyNameContainingIgnoreCase(String academyName);
    List<Academy> findByCityIgnoreCase(String city);
    List<Academy> findByCityIgnoreCaseAndSportListSports(String city, SportsType sports);
    
    @Query("SELECT DISTINCT p.city FROM Academy p")
	List<String> findDistinctCities();
}
