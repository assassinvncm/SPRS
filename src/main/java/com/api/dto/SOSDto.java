package com.api.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonProperty("GPS_Long")
	private String GPS_Long;

	@JsonProperty("GPS_Lati")
	private String GPS_Lati;

	@JsonProperty("user")
	private UserDto u;

	public UserDto getU() {
		return u;
	}

	public void setU(UserDto u) {
		this.u = u;
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

	public String getGPS_Long() {
		return GPS_Long;
	}

	public void setGPS_Long(String gPS_Long) {
		GPS_Long = gPS_Long;
	}

	public String getGPS_Lati() {
		return GPS_Lati;
	}

	public void setGPS_Lati(String gPS_Lati) {
		GPS_Lati = gPS_Lati;
	}
	
}
