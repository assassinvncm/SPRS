package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.MapPointsDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.mapper.MapStructMapper;
import com.api.service.MapService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/manage-map")
public class MapController {

	@Autowired
	MapService mapService;
	
	@Autowired
	MapStructMapper mapStructMapper;

	@RequestMapping(value = "/getReliefPoint", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPoints(@RequestParam("long") double lo, @RequestParam("lat") double lat,
			@RequestParam("radius") double radius) {

		List<ReliefPoint> lstRp = (List<ReliefPoint>) mapService.findPointinRadius(lat, lo, radius, ReliefPoint.class);
		List<ReliefPointDto> lstRpDto = mapStructMapper.lstReliefPointToreliefPointDto(lstRp);
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstRpDto, null));
	}
	
	@RequestMapping(value = "/getPoints", method = RequestMethod.GET)
	public ResponseEntity<?> getPoints(@RequestParam("long") double lo, @RequestParam("lat") double lat,
			@RequestParam("radius") double radius) {

		List<MapPointsDto> lstPoints =  mapService.findAllPoints(lat, lo, radius);
	
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstPoints, null));
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> search(@RequestParam("search") String searchStr) {

		
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", null, null));
	}
}
