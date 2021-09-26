package com.api.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;
import com.api.services.custom.UserServicesCustom;

@Repository
public interface UserServices  extends JpaRepository<User, Long>, UserServicesCustom{
	User findByUsername(String username);
	
	
	
}
