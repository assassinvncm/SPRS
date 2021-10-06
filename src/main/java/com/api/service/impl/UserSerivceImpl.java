package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.api.service.UserService;

@Service
public class UserSerivceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User save(User userDto) {
		// TODO Auto-generated method stub
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		//User user = modelMapper.map(userDto, User.class);
		return userRepository.save(userDto);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

}
