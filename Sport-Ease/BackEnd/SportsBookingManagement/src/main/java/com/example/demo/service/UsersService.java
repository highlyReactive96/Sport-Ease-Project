package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojo.Users;

public interface UsersService {
	List<Users> getAllUsers();
	String addUser(Users user);
	String updateUser(Long id, Users updatedUser);
    String validateUser (Users user);
    Users getUserByEmail(String email);
}
