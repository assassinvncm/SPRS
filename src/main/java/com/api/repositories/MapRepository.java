package com.api.repositories;

import java.util.List;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchResponse;

public interface MapRepository {

	List<MapPointsDto> getPoints(double lati, double longti, double radius);
	
	List<SearchResponse> search(String text, double lati, double longti);
}
