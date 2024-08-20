package com.example.webchat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.webchat.entity.UserEntity;
import com.example.webchat.model.CustomUserDetails;
import com.example.webchat.repository.UserRepository;

@Service
public class UserDetailService  implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserEntity user = userRepository.findByUserId(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found...");
		}
		return new CustomUserDetails(user);
	}
}
