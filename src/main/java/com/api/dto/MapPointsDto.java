package com.api.dto;

import org.springframework.data.geo.Point;

public class MapPointsDto {

	private Long id;
	private String type;
	private Point point;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	
}