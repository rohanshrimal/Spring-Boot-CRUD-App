package com.project.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assignment.model.UserModel;
import com.project.assignment.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserModel addUser(UserModel userModel) {
		return userRepository.save(userModel);	
	}

	public UserModel getUser(Long id) {
		return userRepository.getUserById(id);
	}
}
