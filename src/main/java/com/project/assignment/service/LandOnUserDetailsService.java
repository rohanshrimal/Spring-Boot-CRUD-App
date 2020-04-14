package com.project.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.assignment.auth.LandOnUserPrincipal;
import com.project.assignment.model.UserModel;
import com.project.assignment.repository.UserRepository;

@Service
public class LandOnUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserModel user = this.userRepository.getUserByUsername(userName);
		
		if (null == user) {
			throw new UsernameNotFoundException("Can't find user with username: " + userName);
		} else {
			return new LandOnUserPrincipal(user);
		}
	}

}
