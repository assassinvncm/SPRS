package com.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.SOSDto;
import com.api.dto.UserDto;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.SOSRepository;
import com.api.repositories.UserRepository;
import com.api.service.SOSService;
import com.exception.AppException;

@Service
public class SOSServiceImpl implements SOSService{

	@Autowired
	SOSRepository sosRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MapStructMapper mapStructMapper;

	@Override
	public SOSDto updateStatusSOS(SOSDto sosDto, UserDto udto) {
		User u = userRepo.getById(udto.getId());
		if(null == u) {
			throw new AppException(402,"User is not Found!");
		}
		SOS s = u.getUser_sos();
		s.setDescription(sosDto.getDescription());
		s.setAddress(mapStructMapper.addressDtoToAddress(sosDto.getAddress()));
		s.setLevel(sosDto.getLevel());
		s.setStatus(sosDto.isStatus());
		u.setUser_sos(s);
		userRepo.save(u);
		return sosDto;
	}

}
