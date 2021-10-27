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
	 * @param phone number
	 * @return username
	 */
	String getUsernameByPhone(String phone);
	
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
	
	/**
	 * register user
	 * @param userDto
	 * @return user
	 */
	User registerUser_v2(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	User registerOrganization_v2(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	User registerOrganizationUser_v2(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	User registerStoreUser_v2(User user);
	
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
	
	/**
	 * 
	 * @param 
	 * @return char[]
	 */
	String generatePassword(int len);
}
