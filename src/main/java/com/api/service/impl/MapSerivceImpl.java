package com.api.service.impl;

import org.springframework.stereotype.Service;

import com.api.service.MapService;

@Service
public class MapSerivceImpl implements MapService{

	@Override
	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
		// TODO Auto-generated method stub
	    double R = 6371;
	    double dLat = (la2 - la1) * (Math.PI / 180);
	    double dLon = (lo2 - lo1) * (Math.PI / 180);
	    double la1ToRad = la1 * (Math.PI / 180);
	    double la2ToRad = la2 * (Math.PI / 180);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad)
	                * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = R * c;
	    return d;
	}

}
