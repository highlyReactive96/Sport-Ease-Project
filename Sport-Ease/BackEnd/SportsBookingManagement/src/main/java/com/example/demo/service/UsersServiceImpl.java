package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojo.Role;
import com.example.demo.pojo.Users;
import com.example.demo.repo.UsersRepo;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UsersRepo userRepo;
	
	@Override
	public List<Users> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String addUser(Users user) {
		try {
			user.setRole(Role.ROLE_USER);
			userRepo.save(user);
			return "user added successfully";
		}
		catch(DataIntegrityViolationException e){
			return "email already exists";
		}
			catch (Exception e) {
			return "something went wrong";
		}
	}

	@Override
	public String updateUser(Long id, Users updatedUser) {
		Users oldUser = userRepo.findById(id).orElseThrow(()->new RuntimeException("id not found"));
		oldUser.setPassword(updatedUser.getPassword());
		oldUser.setRole(updatedUser.getRole());
		userRepo.save(oldUser);
		return "updated successfully";
	}

	@Override
	public String validateUser(Users user) {
		Users validUser=userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
		if(validUser==null)
		return "";
		else
		return validUser.getEmail()+":"+validUser.getRole();
	}

	@Override
	public Users getUserByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

}
