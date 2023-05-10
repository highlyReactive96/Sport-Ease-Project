package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Users;
@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
	
	Users findByEmail(String email);
	
	@Query("SELECT u FROM Users u WHERE u.email = :email AND u.password = :password")
    public Users findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
