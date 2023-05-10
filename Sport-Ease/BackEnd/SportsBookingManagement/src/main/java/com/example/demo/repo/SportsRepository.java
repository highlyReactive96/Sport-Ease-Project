package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Sports;
@Repository
public interface SportsRepository extends JpaRepository<Sports, Long> {
    Optional<Sports> findByIdAndAcademyId(Long id, Long academyId);
    List<Sports> findByAcademyId(Long academyId);
}
