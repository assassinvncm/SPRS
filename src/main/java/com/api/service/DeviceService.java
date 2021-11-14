package com.api.service;


import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;

public interface DeviceService {
	
	DeviceDto insertDevice(DeviceDto deviceDto);
	
	
	
	void updateDeviceAddress(AddressDto addressDto);
	
	DeviceDto updateDeviceToken(Long device_Id, String token);
	
	String getDeviceTokenByUserId(Long uId);
	
	List<String> getDeviceTokenByCity(Long city_id);
	
	List<String> getDeviceTokenByDistrict(Long district_id);
	
	List<String> getDeviceTokenBySubDistrict(Long subDistrict_id);
	
	void deleteDeviceToken(Long device_id);
}
