package com.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.controller.UserController;
import com.api.dto.SPRSResponse;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;

@Service
public class UserSerivceImpl implements UserService{

	public static Logger logger = LoggerFactory.getLogger(UserSerivceImpl.class);
	
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

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		logger.info("Start get all User");
		List<User> lst = null;
		try {
			lst = userRepository.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: "+e.getMessage());
			throw new AppException(501,"Error when get user");
		}
		logger.info("End get all User");
		return lst;
	}

}
