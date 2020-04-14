package com.project.assignment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	public void deleteUser(long id) {
		userRepository.deleteById(id);

	}

	public ResponseEntity<UserModel> updateUser(long id, UserModel userModel) {
		Optional<UserModel> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		userModel.setId(id);

		userRepository.save(userModel);

		return ResponseEntity.noContent().build();
	}
}
