package com.api.dto;

public class ReportResultDto {
	private int month;
	private int year;
	private int total;
	private int type_point;
	
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getType_point() {
		return type_point;
	}
	public void setType_point(int type_point) {
		this.type_point = type_point;
	}
}
