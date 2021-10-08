package com.api.service;

import java.util.List;


import com.api.entity.User;

public interface UserService {
	
	/**
	 * 
	 * @param userDto
	 * @return list user
	 */
	List<User> getAllUser();
	
	/**
	 * 
	 * @return user
	 */
	User getUserbyToken();
	
	/**
	 * register user
	 * @param userDto
	 * @return user
	 */
	User registerUser(User user);
	
	User save(User userDto);
	
	/**
	 * 
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	User getOne(Long id);
}
