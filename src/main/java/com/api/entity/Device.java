package com.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Device extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8547873357635626670L;
	
	@Column
	private String token;
	
	@OneToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
    private Address address;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
