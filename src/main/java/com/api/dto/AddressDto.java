package com.api.dto;

import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.SubDistrict;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {
	
	@JsonProperty("city")
	private City city;
	
	@JsonProperty("district")
	private District district;
	
	@JsonProperty("subDistrict")
	private SubDistrict subDistrict;
	
	@JsonProperty("addressLine1")
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	private String addressLine2;
	
	@JsonProperty("GPS_long")
	private String GPS_long;
	
	@JsonProperty("GPS_lati")
	private String GPS_lati;

	
	
	public AddressDto(City city, District district, SubDistrict subDistrict, String addressLine1, String addressLine2,
			String gPS_long, String gPS_lati) {
		super();
		this.city = city;
		this.district = district;
		this.subDistrict = subDistrict;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		GPS_long = gPS_long;
		GPS_lati = gPS_lati;
	}
	
	
	
}
