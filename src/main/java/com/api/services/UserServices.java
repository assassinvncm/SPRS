package com.api.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;
import com.api.services.custom.UserServiceCustom;

@Repository
public interface UserServices  extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
