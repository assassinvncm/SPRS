package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReliefPointDto;
import com.api.dto.StoreDto;
import com.api.entity.Address;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.StoreDetail;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.StoreRepository;
import com.api.service.AddressService;
import com.api.service.StoreService;
import com.exception.AppException;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	AddressService addressService;
	
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
	public Store createStore(StoreDto s) {
		// TODO Auto-generated method stub
		Store store = mapStructMapper.storeDtoToStore(s);
		List<StoreDetail> lstSTDetail = store.getStoreDetail().stream().map(st ->{
			st.setStorePoint(store);
			return st;
		}).collect(Collectors.toList());
		store.setStoreDetail(lstSTDetail);
		Address address = addressService.mapAddress(s.getAddress());
		store.setLocation(address);
		Store str = storeRepository.save(store);
		return str;
	}

	@Override
	public Store updateStore(Store s) {
		// TODO Auto-generated method stub
		Store st = storeRepository.getById(s.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		BeanUtils.copyProperties(st, s);
		
		return storeRepository.save(s);
	}

	@Override
	public List<StoreDto> getAllStore() {
		// TODO Auto-generated method stub
		List<Store> lstStore = storeRepository.findAll();
		return mapStructMapper.lstStoreToStoreDto(lstStore);
	}
}
