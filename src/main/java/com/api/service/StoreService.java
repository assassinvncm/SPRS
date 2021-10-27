package com.api.service;

import java.util.List;

import com.api.entity.Address;
import com.api.entity.Store;
import com.api.entity.User;

public interface StoreService {
	/**
	 * 
	 * @return
	 */
	Store getStoreById(Long id);
	
	/**
	 * 
	 * @return
	 */
	Store getStoreByUser(User user);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<Store> getStoreByLocation(Address address);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<Store> getStoreByArea(Address address);
	
	/**
	 * 
	 * @param Store
	 * @return 
	 */
	Store createStore(Store s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store updateStore(Store s);
}
