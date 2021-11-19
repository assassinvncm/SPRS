package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.entity.Address;
import com.api.entity.Device;
import com.api.mapper.MapStructMapper;
import com.api.repositories.DeviceRepository;
import com.api.service.AddressService;
import com.api.service.DeviceService;
import com.api.service.NotificationService;
import com.exception.AppException;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	MapStructMapper mapStructMapper;

	@Autowired
	AddressService addressService;
	
	@Autowired
	NotificationService notificationService;

	@Override
	public DeviceDto insertDevice(DeviceDto deviceDto) {
		// TODO Auto-generated method stub
		if(checkUserLoginAnotherDevice((long)1,deviceDto.getSerial())) {
			//send notification
			notificationService.sendPnsToDevice(null);
			//delete device in db by userId
			deviceRepository.deleteByUserId(null);
		}
		
		//insert db
		Device device = mapStructMapper.deviceDtoToDevice(deviceDto);
		Address address = addressService.mapAddress(deviceDto.getAddress());
		device.setAddress(address);
		Device responseDevice = deviceRepository.save(device);
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Transactional
	@Override
	public DeviceDto updateDevice(DeviceDto deviceDto) {
		// TODO Auto-generated method stub

		deviceRepository.findById(deviceDto.getId())
				.orElseThrow(() -> new AppException(403, "Device not exist"));
		
		Device device = mapStructMapper.deviceDtoToDevice(deviceDto);
		Address address = addressService.mapAddress(deviceDto.getAddress());
		device.setAddress(address);
		Device responseDevice = deviceRepository.save(device);
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Override
	public void updateDeviceAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		if (addressDto.getId() == 0) {
			throw new AppException(402, "address id not found");
		}
		addressService.saveAddress(addressDto);

	}

	@Override
	public DeviceDto updateDeviceToken(Long device_Id, String token) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(device_Id)
				.orElseThrow(() -> new AppException(403, "Device not exist"));
		device.setToken(token);
		Device responseDevice = deviceRepository.save(device);
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Override
	public Device getDeviceTokenByUserId(Long uId) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(uId)
				.orElseThrow(() -> new AppException(403, "User Id not exist"));
		return device;
	}
	
	@Override
	public List<String> getDeviceTokenByStoreId(Long sId) {
		// TODO Auto-generated method stub
		return deviceRepository.findTokenUserByStore(sId);
	}

	@Override
	public List<String> getDeviceTokenByCity(Long city_id) {
		// TODO Auto-generated method stub
		return deviceRepository.findTokenUserByCityId(city_id);
	}

	@Override
	public List<String> getDeviceTokenByDistrict(Long district_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDeviceTokenBySubDistrict(Long subDistrict_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteDeviceByUserId(Long uId) {
		// TODO Auto-generated method stub
		deviceRepository.deleteByUserId(uId);
	}


	@Override
	public void deleteDeviceToken(Long device_id) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(device_id).orElseThrow(() -> new AppException());
		device.setToken(null);
		Device responseDevice = deviceRepository.save(device);
	}
	
	public boolean checkUserLoginAnotherDevice(Long uId, String serialNum) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findDeviceByUserId(uId);
		if(device == null) {
			return false;
		}
		if(!device.getSerial().equalsIgnoreCase(serialNum)) {
			return false;
		}
		return true;
	}



}
