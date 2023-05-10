package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.AcademyOwner;

@Repository
public interface AcademyOwnerRepository extends JpaRepository<AcademyOwner, Long> {
	Optional<AcademyOwner> findByEmail(String email);
}
