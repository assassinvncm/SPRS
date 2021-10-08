package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Location")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3258989781039410487L;

	@Column(name = "City")
	private String city;
	
	@Column(name = "Province")
	private String province;
	
	@Column(name = "SubDistrict")
	private String subDistrict;
	
	@Column(name = "Longitude")
	private String longitude;
	
	@Column(name = "Latitude")
	private String latitude;
	
	
}
