package com.jwt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
		if(user.getIsActive() == false) {//user.getGroups_user().get(0).getPermissions().get(0)
			throw new AuthenException("Account is not active");
		}
//		Set<String> authorities = new HashSet<>();
//        if (null != user.getGroups_user()) user.getGroups_user().forEach(r -> {
//            authorities.add(r.getCode());
//            r.getPermissions().forEach(p -> authorities.add(p.getCode()));
//        });
//        user.setAuthorities(authorities);
		return UserDetailsImpl.build(user);
	}
}