package com.api.service;

import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.entity.Device;

public interface DeviceService {

	/**
	 * Insert device of user
	 * 
	 * @param deviceDto
	 * @return DeviceDto
	 */
	DeviceDto insertDevice(DeviceDto deviceDto);

	/**
	 * update information of device when login another device
	 * 
	 * @param deviceDto
	 * @return DeviceDto
	 */
	DeviceDto updateDevice(DeviceDto deviceDto);

	/**
	 * update address of device when move
	 * 
	 * @param addressDto
	 */
	void updateDeviceAddress(AddressDto addressDto);

	/**
	 * update new token
	 * 
	 * @param device_Id
	 * @param token
	 * @return
	 */
	DeviceDto updateDeviceToken(Long device_Id, String token);

	/**
	 * get list token that subcribe store
	 * 
	 * @param uId
	 * @return
	 */
	
	List<String> getDeviceTokenByStoreId(Long sId);

	/**
	 * get list token in a city
	 * 
	 * @param city_id
	 * @return
	 */

	Device getDeviceTokenByUserId(Long uId);

	/**
	 * get list token in a city
	 * 
	 * @param city_id
	 * @return
	 */
	List<String> getDeviceTokenByCity(Long city_id);

	List<String> getDeviceTokenByDistrict(Long district_id);

	List<String> getDeviceTokenBySubDistrict(Long subDistrict_id);

	void deleteDeviceToken(Long device_id);
	
	void deleteDeviceByUserId(Long uId);
}
