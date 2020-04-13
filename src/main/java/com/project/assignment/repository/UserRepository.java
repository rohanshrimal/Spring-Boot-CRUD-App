package com.project.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.assignment.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel getUserById(Long id);

}
