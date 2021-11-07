package com.api.service;

import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.ReliefPointDto;
import com.api.entity.Address;
import com.api.entity.ReliefPoint;
import com.api.entity.User;

public interface ReliefPointService {
	
	/**
	 * 
	 * @return
	 */
	List<ReliefPointDto> getAllReliefPoint();
	
	/**
	 * 
	 * @return
	 */
	ReliefPoint getReliefPointById(Long id);
	
	/**
	 * 
	 * @return
	 */
	ReliefPointDto getReliefPointByIdAndUser(Long rpId, Long uId);
	
	/**
	 * 
	 * @return
	 */
	ReliefPoint getReliefPointByUser(User user);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<ReliefPoint> getReliefPointByLocation(Address address);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<ReliefPointDto> getReliefPointByArea(AddressDto addressDto);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return 
	 */
	ReliefPoint createReliefPoint(ReliefPointDto reliefPointDto);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateReliefPoint(ReliefPoint reliefPoint);
}
