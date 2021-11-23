package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.api.dto.MapPointsDto;
import com.api.entity.Organization;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.ReliefPointRepository;
import com.api.repositories.StoreRepository;
import com.api.service.MapService;

@Service
public class MapSerivceImpl implements MapService {

	@Autowired
	ReliefPointRepository reliefPointRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	StoreRepository storeRepository;

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

	@Override
	public List<MapPointsDto> findAllPoints(double la, double lo, double radius) {
		// TODO Auto-generated method stub
		List<ReliefPoint> rps = reliefPointRepository.findAll();
		List<Store> stores = storeRepository.findAll();
		List<Organization> orgs = organizationRepository.findAll();

		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();

		for (ReliefPoint rp : rps) {
			MapPointsDto mp = new MapPointsDto();
			if (rp.getAddress().getGPS_Lati() == null || rp.getAddress().getGPS_Lati().isBlank()
					|| rp.getAddress().getGPS_Long() == null || rp.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			mp.setId(rp.getId());
			mp.setType("rp");
			mp.setPoint(new Point(Double.parseDouble(rp.getAddress().getGPS_Lati()),
					Double.parseDouble(rp.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}

		for (Store store : stores) {
			if (store.getLocation().getGPS_Lati() == null || store.getLocation().getGPS_Lati().isBlank()
					|| store.getLocation().getGPS_Long() == null || store.getLocation().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(store.getId());
			mp.setType("st");
			mp.setPoint(new Point(Double.parseDouble(store.getLocation().getGPS_Lati()),
					Double.parseDouble(store.getLocation().getGPS_Long())));
			mapPoint.add(mp);
		}

		for (Organization org : orgs) {
			if (org.getAddress().getGPS_Lati() == null || org.getAddress().getGPS_Lati().isBlank()
					|| org.getAddress().getGPS_Long() == null || org.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(org.getId());
			mp.setType("org");
			mp.setPoint(new Point(Double.parseDouble(org.getAddress().getGPS_Lati()),
					Double.parseDouble(org.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}

		mapPoint = mapPoint.stream().filter((mp) -> {
			double distance = distanceBetween2Points(mp.getPoint().getX(), mp.getPoint().getY(), la, lo);
			if (distance > radius)
				return false;
			return true;
		}).collect(Collectors.toList());

		return mapPoint;
	}

	@Override
	public void search(String searchStr) {
		// TODO Auto-generated method stub
		
	}

}
