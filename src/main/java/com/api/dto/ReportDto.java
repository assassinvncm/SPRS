package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDto {
	
	@JsonProperty("district_id")
	private int district_id;
	
	@JsonProperty("sub_district_id")
	private int sub_district_id;
	
	@JsonProperty("city_id")
	private int city_id;
	
	@JsonProperty("date_from")
	private String date_from;
	
	@JsonProperty("date_to")
	private String date_to;
	
	@JsonProperty("type_time")
	private int type_time;
	
	@JsonProperty("type_chart")
	private int type_chart;
	
	@JsonProperty("type_point")
	private int[] type_point;
	
	@JsonProperty("org_id")
	private Long org_id;
	
	public ReportDto() {
		super();
		date_from = "";
		date_to = "";
	}

	/**
	 * @return the org_id
	 */
	public Long getOrg_id() {
		return org_id;
	}

	/**
	 * @param org_id the org_id to set
	 */
	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	public String getDate_from() {
		return date_from;
	}

	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}

	public String getDate_to() {
		return date_to;
	}

	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}

	public int getType_time() {
		return type_time;
	}

	public void setType_time(int type_time) {
		this.type_time = type_time;
	}

	public int getType_chart() {
		return type_chart;
	}

	public void setType_chart(int type_chart) {
		this.type_chart = type_chart;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	public int getSub_district_id() {
		return sub_district_id;
	}

	public void setSub_district_id(int sub_district_id) {
		this.sub_district_id = sub_district_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int[] getType_point() {
		return type_point;
	}

	public void setType_point(int[] type_point) {
		this.type_point = type_point;
	}
}
