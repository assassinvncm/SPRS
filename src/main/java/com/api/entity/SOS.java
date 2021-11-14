package com.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_SOS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SOS extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837417697393301503L;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "close_time")
	private int level;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "GPS_Long")
	@JsonProperty("GPS_Long")
	private String GPS_Long;
	
	@Column(name = "GPS_Lati")
	@JsonProperty("GPS_Lati")
	private String GPS_Lati;

	public SOS() {
		super();
	}

	public SOS(String description, int level, boolean status, String gPS_Long, String gPS_Lati) {
		super();
		this.description = description;
		this.level = level;
		this.status = status;
		GPS_Long = gPS_Long;
		GPS_Lati = gPS_Lati;
	}

	public SOS(boolean status) {
		super();
		this.status = status;
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
