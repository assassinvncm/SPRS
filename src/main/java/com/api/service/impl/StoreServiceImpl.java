package com.api.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Address;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.repositories.StoreRepository;
import com.api.service.StoreService;
import com.exception.AppException;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public Store getStoreById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store getStoreByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getStoreByLocation(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getStoreByArea(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store createStore(Store s) {
		// TODO Auto-generated method stub
		Store rp = storeRepository.save(s);
		return rp;
	}

	@Override
	public Store updateStore(Store s) {
		// TODO Auto-generated method stub
		Store rp = storeRepository.getById(s.getId());
		if(null == rp) {
			throw new AppException(402,"Store is not Found!");
		}
		BeanUtils.copyProperties(rp, s);
		
		return storeRepository.save(s);
	}
}
