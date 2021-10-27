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
	
	

	
	
}
