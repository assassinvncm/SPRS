package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReliefPointDto;
import com.api.dto.StoreDto;
import com.api.entity.Address;
import com.api.entity.Image;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.StoreCategory;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.StoreFilterMapper;
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
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Store getStoreById(Long id) {
		Store st = storeRepository.getById(id);
		return st;
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
//		List<StoreCategory> lstSTCate = store.getStore_category().stream().map(st ->{
//			st.setId(store.getId());
//			return st;
//		}).collect(Collectors.toList());
//		store.setStore_category(lstSTCate);
		Store str = new Store();
		try {
			Address address = addressService.mapAddress(s.getAddress());
			store.setLocation(address);
			store.setStatus(true); 
			str = storeRepository.save(store);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public Store updateStoreImg(Store s, String img_url) {
		// TODO Auto-generated method stub
		Store st = storeRepository.getById(s.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		st.getLstImage().add(new Image(st, img_url));
		
		return storeRepository.save(s);
	}

	@Override
	public List<StoreDto> getAllStore() {
		// TODO Auto-generated method stub
		List<Store> lstStore = storeRepository.findAll();
		return mapStructMapper.lstStoreToStoreDto(lstStore);
	}

	@Override
	public Store deleteStore(StoreDto s) {
		Store st = storeRepository.getById(s.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		storeRepository.delete(st);
		return st;
	}

	@Override
	public List<StoreDto> getStoreOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getStoreFilterByType() {
		List<User> stm = storeRepository.filterStoreByStatusType(true, "yess");
		return stm;
	}
}
