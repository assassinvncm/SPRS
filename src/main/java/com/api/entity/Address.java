package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_Address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3712148124115036984L;
	
	@Column(name = "addressLine")
	@JsonProperty("addressLine")
	private String addressLine;
	
	@Column(name = "GPS_Long")
	@JsonProperty("GPS_Long")
	private String GPS_Long;
	
	@Column(name = "GPS_Lati")
	@JsonProperty("GPS_Lati")
	private String GPS_Lati;
	
	@ManyToOne
	@JoinColumn(name = "subDistrict_id",referencedColumnName="id")
	SubDistrict subDistrict;
	

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
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

	public Address() {
		super();
	}

	public Address(String city, String province, String district, SubDistrict subDistrict, String addressLine,
			String gPS_Long, String gPS_Lati) {
		super();
		this.subDistrict = subDistrict;
		this.addressLine = addressLine;
		GPS_Long = gPS_Long;
		GPS_Lati = gPS_Lati;
	}
	
	
}
