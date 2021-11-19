package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.entity.ReliefPoint;
import com.api.service.DeviceService;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/device")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDevice(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody DeviceDto deviceDto) {
		deviceService.insertDevice(deviceDto);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDevice(@RequestHeader("Authorization") String requestTokenHeader) {

		deviceService.deleteDeviceByUserId(null);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceToken(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("token") String token) {

		deviceService.updateDeviceToken(null,token);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceAddress(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody AddressDto addressDto) {

		deviceService.updateDeviceAddress(addressDto);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}
}
