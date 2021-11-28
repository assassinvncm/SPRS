package com.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.dto.AddressDto;
import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.entity.Address;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;

public interface ReliefPointService {
	
	/**
	 * 
	 * @return
	 */
	List<ReliefPointDto> getReliefPoints(Long uId,ReliefPointFilterDto reliefPointFilterDto);
	
	/**
	 * 
	 * @param uId
	 * @param reliefPointFilterDto
	 * @return
	 */
	//PagingResponse<ReliefPointDto> getReliefPoints_v2(Long uId, ReliefPointFilterDto reliefPointFilterDto);
	
	/**
	 * get  reliefpoint by id
	 * @return
	 */
	ReliefPointDto getReliefPointById(Long id);
	
	/**
	 * 
	 * @return
	 */
	ReliefPointDto getReliefPointByIdAndUser(Long rpId, Long uId);
	
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
	ReliefPoint updateReliefPoint(ReliefPointDto reliefPoint);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateStatusReliefPoint(Long rId, Boolean status);
	
	/**
	 * 
	 * @param rId
	 * 
	 */
	void deleteReliefPointById(Long rId);
	
	/**
	 * 
	 * @param MultipartFile, store_id
	 * @return
	 */
	ReliefPoint uploadReliefImg(MultipartFile file, String relief_id);
}
