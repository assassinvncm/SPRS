package com.api.dto;

import java.sql.Date;
import java.util.List;

import com.api.entity.ReliefInformation;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReliefPointDto {
	
	private long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;

	
	private Date open_time;

	private Date close_time;
	
	private String status;
	
	private UserDto user_rp;
	
	@JsonProperty("reliefInformations")
	private List<ReliefInformationDto> reliefInformations;
	
	@JsonProperty("address")
	private AddressDto address;
	
	

	public ReliefPointDto() {
		super();
	}

	public ReliefPointDto(long id, String name, String description, Date open_time, Date close_time, String status,
			UserDto user_rp, List<ReliefInformationDto> reliefInformations, AddressDto address) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.open_time = open_time;
		this.close_time = close_time;
		this.status = status;
		this.user_rp = user_rp;
		this.reliefInformations = reliefInformations;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}

	public Date getClose_time() {
		return close_time;
	}

	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getUser_rp() {
		return user_rp;
	}

	public void setUser_rp(UserDto user_rp) {
		this.user_rp = user_rp;
	}

	public List<ReliefInformationDto> getReliefInformations() {
		return reliefInformations;
	}

	public void setReliefInformations(List<ReliefInformationDto> reliefInformations) {
		this.reliefInformations = reliefInformations;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	
	
	

	
	
}
