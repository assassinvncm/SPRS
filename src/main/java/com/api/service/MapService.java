package com.api.service;

import java.util.List;


public interface MapService {

	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2);

	public List<?> findPointinRadius(double la, double lo, double radius, Class<?> destinationType);
}
