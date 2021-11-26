package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.dto.SPRSResponse;
import com.api.entity.User;
import com.api.service.DeviceService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/device")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDevice(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody DeviceDto deviceDto) {
		User user = userService.getUserbyTokenAuth(requestTokenHeader);

		deviceService.insertDevice(user, deviceDto);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDevice(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("serialNumber") String serial) {

		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		deviceService.deleteDeviceByUserIdAndSeri(user.getId(), serial);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/update/token", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceToken(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("token") String token) {

		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		deviceService.updateDeviceToken(user.getId(), token);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceAddress(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody AddressDto addressDto, @PathVariable("id") long device_id) {

		deviceService.updateDeviceAddress(device_id, addressDto);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}
}
