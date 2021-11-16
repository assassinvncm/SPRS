package com.api.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class SOSDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541843061802968807L;
	
	private long id;

	@JsonProperty("description")
	private String description;

	@JsonProperty("level")
	private int level;

	@JsonProperty("status")
	private boolean status;
	
	@JsonProperty("address")
	@NotNull
	private AddressDto address;

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
