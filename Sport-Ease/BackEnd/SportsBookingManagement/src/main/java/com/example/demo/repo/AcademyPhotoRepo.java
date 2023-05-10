package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.AcademyPhotos;
@Repository
public interface AcademyPhotoRepo extends JpaRepository<AcademyPhotos,Long> {

}
