package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.ReliefPoint;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.service.MapService;

@Service
public class MapSerivceImpl implements MapService {

	@Autowired
	ReliefPointRepository reliefPointRepository;
	
	

	@Override
	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
		// TODO Auto-generated method stub
		double R = 6371;
		double dLat = (la2 - la1) * (Math.PI / 180);
		double dLon = (lo2 - lo1) * (Math.PI / 180);
		double la1ToRad = la1 * (Math.PI / 180);
		double la2ToRad = la2 * (Math.PI / 180);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;
	}

	@Override
	public List<?> findPointinRadius(double la, double lo, double radius, Class<?> destinationType) {
		// TODO Auto-generated method stub
		List<?> lstRs = null;
		if (destinationType.equals(ReliefPoint.class)) {
			List<ReliefPoint> lstRp = reliefPointRepository.findAll();
			lstRs = lstRp.stream().filter((rp) -> {
				double distance = distanceBetween2Points(Double.parseDouble(rp.getAddress().getGPS_Lati()),
						Double.parseDouble(rp.getAddress().getGPS_Long()), la, lo);
				if (distance > radius)
					return false;
				return true;
			}).collect(Collectors.toList());

		}
		
		return lstRs;
	}

}
