package com.project.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.assignment.model.UserModel;
import com.project.assignment.service.UserService;

@RestController
@RequestMapping
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}")
	public UserModel getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PostMapping("/user")
	public UserModel addUser(@RequestBody UserModel userModel) {
		return userService.addUser(userModel);
	}

}
