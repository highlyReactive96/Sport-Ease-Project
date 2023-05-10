package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Users;
import com.example.demo.service.UsersService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	UsersService userService;
	
	@GetMapping("/allUsers")
	public List<Users> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody Users user)
	{
		return userService.addUser(user);
	}
	
	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id,@RequestBody Users updatedUser)
	{
		try
		{
			String msg = userService.updateUser(id, updatedUser);
			return msg;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
