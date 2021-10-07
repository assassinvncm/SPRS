package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "SPRS_Location")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {
	
	@Id
	private Long id;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "City")
	private String province;
	
	@Column(name = "City")
	private String subDistrict;
	
	@Column(name = "Longitude")
	private String longitude;
	
	@Column(name = "Latitude")
	private String latitude;
}
