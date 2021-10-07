package com.jwt.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;

	//private User user;
	private Long id;
	private String username;
	private String phoneNumber;
	
	@JsonIgnore
	private String password;

	Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
		super(); 
	}
	
	public UserDetailsImpl(Long id, String username,String phoneNumber,String password, Collection<? extends GrantedAuthority> authories) {
		super();
		//this.user = user;
		this.id = id;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.authorities = authories;
	}

	public static UserDetailsImpl build(User user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(),user.getUsername(),user.getPhone(),user.getPassword(),Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return 	Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
