package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.AddressDto;
import com.api.dto.ReliefPointDto;
import com.api.entity.Address;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.service.ReliefPointService;
import com.exception.AppException;

@Service
public class ReliefPointServiceImpl implements ReliefPointService {

	@Autowired
	ReliefPointRepository reliefPointRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Override
	public ReliefPoint getReliefPointById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReliefPoint getReliefPointByUser(User user) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<ReliefPoint> getReliefPointByLocation(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReliefPointDto> getReliefPointByArea(AddressDto addressDto) {
		// TODO Auto-generated method stub
		List<ReliefPoint> rp = reliefPointRepository.findReliefPointByArea(addressDto.getId());
		
		
		return null;
	}

	@Override
	public ReliefPoint createReliefPoint(ReliefPointDto reliefPointDto) {
		// TODO Auto-generated method stub
		ReliefPoint reliefPoint = mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto);
		List<ReliefInformation> lstRIfor = reliefPoint.getReliefInformations().stream().map(rf ->{
			rf.setReliefPoint(reliefPoint);
			return rf;
		}).collect(Collectors.toList());
		reliefPoint.setReliefInformations(lstRIfor);
		ReliefPoint rp = reliefPointRepository.save(reliefPoint);
		return rp;
	}

	@Override
	public ReliefPoint updateReliefPoint(ReliefPoint reliefPoint) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.getById(reliefPoint.getId());
		if(null == rp) {
			throw new AppException(402,"Relief point is not Found!");
		}
		BeanUtils.copyProperties(rp, reliefPoint);
		
		return reliefPointRepository.save(reliefPoint);
	}
	
}
