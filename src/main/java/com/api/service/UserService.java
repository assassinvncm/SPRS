package com.api.service;

import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
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
	UserDto getUserbyToken(String requestTokenHeader);
	
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
	void registerUser_v2(UserDto userDto);

	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerOrganization_v2(UserDto userDto);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerOrganizationUser_v2(UserDto userDto);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerStoreUser_v2(UserDto userDto);
	
	/**
	 * 
	 * @param userDto
	 */
	void updateUser(UserDto userDto,UserDto bean);
	
	/**
	 * @param newPassword
	 */
	void updatePassword(UserDto userDto, String newPassword);
	
	
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
