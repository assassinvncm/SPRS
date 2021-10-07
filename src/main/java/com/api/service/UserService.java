package com.api.service;

import java.util.List;


import com.api.entity.User;

public interface UserService {
	
	
	List<User> getAllUser();
	
	/**
	 * 
	 * @param userDto
	 * @return
	 */
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
