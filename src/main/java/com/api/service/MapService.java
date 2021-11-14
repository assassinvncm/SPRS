package com.api.service;

import java.util.List;

import com.api.dto.MapPointsDto;


public interface MapService {

	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2);

	public List<?> findPointinRadius(double la, double lo, double radius, Class<?> destinationType);
	
	public List<MapPointsDto> findAllPoints(double la, double lo, double radius);
	
	public void search(String searchStr);
}
