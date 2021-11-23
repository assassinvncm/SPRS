package com.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.dto.MapPointsDto;
import com.api.dto.Prediction;
import com.api.dto.SearchResponse;
import com.api.entity.Organization;
import com.api.entity.ReliefPoint;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.ReliefPointRepository;
import com.api.repositories.SOSRepository;
import com.api.repositories.StoreRepository;
import com.api.service.MapService;
import com.api.service.SOSService;
import com.ultils.Constants;

@Service
public class MapSerivceImpl implements MapService {

	@Autowired
	ReliefPointRepository reliefPointRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	SOSRepository SOSRepository;

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

	public List<MapPointsDto> findReliefPoint(double la, double lo, double radius) {
		List<ReliefPoint> rps = reliefPointRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (ReliefPoint rp : rps) {
			MapPointsDto mp = new MapPointsDto();
			if (rp.getAddress().getGPS_Lati() == null || rp.getAddress().getGPS_Lati().isBlank()
					|| rp.getAddress().getGPS_Long() == null || rp.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			mp.setId(rp.getId());
			mp.setType(Constants.MAP_TYPE_RELIEFPOINT);
			mp.setPoint(new Point(Double.parseDouble(rp.getAddress().getGPS_Lati()),
					Double.parseDouble(rp.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}
		return mapPoint;
	}

	public List<MapPointsDto> findStores(double la, double lo, double radius) {
		List<Store> stores = storeRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (Store store : stores) {
			if (store.getLocation().getGPS_Lati() == null || store.getLocation().getGPS_Lati().isBlank()
					|| store.getLocation().getGPS_Long() == null || store.getLocation().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(store.getId());
			mp.setType(Constants.MAP_TYPE_STORE);
			mp.setPoint(new Point(Double.parseDouble(store.getLocation().getGPS_Lati()),
					Double.parseDouble(store.getLocation().getGPS_Long())));
			mp.setName(store.getName());
			mapPoint.add(mp);
		}
		return mapPoint;
	}

	public List<MapPointsDto> findOrganization(double la, double lo, double radius) {
		List<Organization> orgs = organizationRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (Organization org : orgs) {
			if (org.getAddress().getGPS_Lati() == null || org.getAddress().getGPS_Lati().isBlank()
					|| org.getAddress().getGPS_Long() == null || org.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(org.getId());
			mp.setType(Constants.MAP_TYPE_ORGANIZATION);
			mp.setPoint(new Point(Double.parseDouble(org.getAddress().getGPS_Lati()),
					Double.parseDouble(org.getAddress().getGPS_Long())));
			mp.setName(org.getName());
			mapPoint.add(mp);
		}

		return mapPoint;
	}

	public List<MapPointsDto> findSOS(double la, double lo, double radius) {
		List<SOS> SOSs = SOSRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (SOS sos : SOSs) {
			if (sos.getAddress().getGPS_Lati() == null || sos.getAddress().getGPS_Lati().isBlank()
					|| sos.getAddress().getGPS_Long() == null || sos.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(sos.getId());
			mp.setType(Constants.MAP_TYPE_SOS);
			mp.setPoint(new Point(Double.parseDouble(sos.getAddress().getGPS_Lati()),
					Double.parseDouble(sos.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}

		return mapPoint;
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
			mp.setType(Constants.MAP_TYPE_RELIEFPOINT);
			mp.setPoint(new Point(Double.parseDouble(rp.getAddress().getGPS_Lati()),
					Double.parseDouble(rp.getAddress().getGPS_Long())));
			mp.setName(rp.getName());
			mapPoint.add(mp);
		}

		for (Store store : stores) {
			if (store.getLocation().getGPS_Lati() == null || store.getLocation().getGPS_Lati().isBlank()
					|| store.getLocation().getGPS_Long() == null || store.getLocation().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(store.getId());
			mp.setType(Constants.MAP_TYPE_STORE);
			mp.setPoint(new Point(Double.parseDouble(store.getLocation().getGPS_Lati()),
					Double.parseDouble(store.getLocation().getGPS_Long())));
			mp.setName(store.getName());
			mapPoint.add(mp);
		}

		for (Organization org : orgs) {
			if (org.getAddress().getGPS_Lati() == null || org.getAddress().getGPS_Lati().isBlank()
					|| org.getAddress().getGPS_Long() == null || org.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(org.getId());
			mp.setType(Constants.MAP_TYPE_ORGANIZATION);
			mp.setPoint(new Point(Double.parseDouble(org.getAddress().getGPS_Lati()),
					Double.parseDouble(org.getAddress().getGPS_Long())));
			mp.setName(org.getName());
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

	public void search(String searchStr) {
		// TODO Auto-generated method stub

	}

	@Value("${goong.map.api.key}")
	private String api_key;

	public void search(String text, double lati, double longti) {
		
		SearchResponse searchResponse = searchApiGoongMap(text, lati, longti);
	}

	public SearchResponse searchApiGoongMap(String searchText, double lati, double longti) {

		String url = "https://rsapi.goong.io/Place/AutoComplete?api_key=" + api_key + "&location=" + lati + "," + longti
				+ "&input=" + searchText;

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Yêu cầu trả về định dạng JSON
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HttpEntity<String>: To get result as String.
		HttpEntity<SearchResponse> entity = new HttpEntity<SearchResponse>(headers);

		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Gửi yêu cầu với phương thức GET, và các thông tin Headers.
		ResponseEntity<SearchResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, SearchResponse.class);
	
		HttpStatus statusCode = response.getStatusCode();
		
		SearchResponse searchResponse = null;
		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			searchResponse = response.getBody();
		}
		return searchResponse;

	}

}
