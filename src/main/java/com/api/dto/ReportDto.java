package com.api.dto;

import java.util.List;

import com.api.entity.Address;

public class ReportDto {
	private String district;
	private String sub_district;
	private String city;
	private int month;
	private int year;
	private int type_point;

	private List lstRs;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSub_district() {
		return sub_district;
	}

	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getType_point() {
		return type_point;
	}

	public void setType_point(int type_point) {
		this.type_point = type_point;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List getLstRs() {
		return lstRs;
	}

	public void setLstRs(List lstRs) {
		this.lstRs = lstRs;
	}
	
}
