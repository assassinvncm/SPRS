package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.SPRSResponse;
import com.api.dto.SubDistrictDto;
import com.api.entity.City;
import com.api.service.AddressService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/address")
public class AddressController {
	public static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping(value = "/city", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCity() {
		logger.info("Start get all Citys");
		List<CityDto> cityDtos = addressService.getAllCity();
		logger.info("End get all Citys");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", cityDtos, null));
	}
	
	@RequestMapping(value = "/district/{cityId}", method = RequestMethod.GET)
	public ResponseEntity<?> getDistrict(@PathVariable("cityId") long cityId) {
		logger.info("Start get list district by CityID");
		List<DistrictDto> districtDtos = addressService.getDistrictByCityId(cityId);
		logger.info("End get list district by CityID");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", districtDtos, null));
	}
	
	@RequestMapping(value = "/subdistrict/{districtId}", method = RequestMethod.GET)
	public ResponseEntity<?> getSubDistrict(@PathVariable("districtId") long districtId) {
		logger.info("Start get list subdistrict by DistrictID");
		List<SubDistrictDto> subDistrictDtos = addressService.getSDByDistrictId(districtId);
		logger.info("End get list subdistrict by DistrictID");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", subDistrictDtos, null));
	}
	
	
}
