package com.jwt.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.controller.UserController;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.exception.AppException;
import com.exception.AuthenException;
import com.jwt.entity.UserDetailsImpl;


@Service
public class JwtUserDetailsService implements UserDetailsService {


	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,AuthenException {
		User user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		if(user.getIsActive() == false) {
			throw new AuthenException("Account is not active");
		}
		return UserDetailsImpl.build(user);
		
	}
}