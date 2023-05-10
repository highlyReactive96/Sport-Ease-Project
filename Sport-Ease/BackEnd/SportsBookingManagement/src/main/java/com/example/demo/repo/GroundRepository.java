package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Ground;
import com.example.demo.pojo.Sports;
@Repository
public interface GroundRepository extends JpaRepository<Ground, Long>{
	  List<Ground> findBySports(Sports sports) ;
}
