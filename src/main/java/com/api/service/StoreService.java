package com.api.service;

import java.util.List;

import com.api.dto.ReliefPointDto;
import com.api.dto.StoreDto;
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

	Store createStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return 
	 */

	Store deleteStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store updateStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store openCloseStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store updateStoreImg(Store s, String img_url);
	
	/**
	 * 
	 * @return
	 */
	List<StoreDto> getAllStore();
	
	/**
	 * 
	 * @return
	 */
	List<StoreDto> getStoreOwner();
	
	/**
	 * List<StoreFilterMapper>
	 * @return
	 */
	List<StoreDto> getStoreFilterByType(long user_id, int status, String type, int page_size, int page_index);
}
