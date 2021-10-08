package com.api.service;

import java.util.List;

import com.api.entity.Location;
import com.api.entity.ReliefPoint;
import com.api.entity.User;

public interface ReliefPointService {
	
	/**
	 * 
	 * @return
	 */
	ReliefPoint getReliefPointById(Long id);
	
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
	List<ReliefPoint> getReliefPointByLocation(Location location);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<ReliefPoint> getReliefPointByArea(Location location);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return 
	 */
	ReliefPoint createReliefPoint(ReliefPoint reliefPoint);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateReliefPoint(ReliefPoint reliefPoint);
}
