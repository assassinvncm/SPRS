package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "city")
	@JsonProperty("city")
	private String city;
	
	@Column(name = "province")
	@JsonProperty("province")
	private String province;
	
	@Column(name = "district")
	@JsonProperty("district")
	private String district;
	
	@Column(name = "subDistrict")
	@JsonProperty("subDistrict")
	private String subDistrict;
	
	@Column(name = "addressLine")
	@JsonProperty("addressLine")
	private String addressLine;
	
	@Column(name = "GPS_Long")
	@JsonProperty("GPS_Long")
	private String GPS_Long;
	
	@Column(name = "GPS_Lati")
	@JsonProperty("GPS_Lati")
	private String GPS_Lati;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

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

	public Address(String city, String province, String district, String subDistrict, String addressLine,
			String gPS_Long, String gPS_Lati) {
		super();
		this.city = city;
		this.province = province;
		this.district = district;
		this.subDistrict = subDistrict;
		this.addressLine = addressLine;
		GPS_Long = gPS_Long;
		GPS_Lati = gPS_Lati;
	}
	
	
}
