package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReliefPointDto;
import com.api.dto.StoreCategoryDto;
import com.api.dto.StoreDto;
import com.api.entity.Address;
import com.api.entity.Image;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.StoreCategory;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.StoreRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.StoreService;
import com.common.utils.DateUtils;
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
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Autowired
	NotificationService notificationService;
	
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
			store.setStatus(1); 
			str = storeRepository.save(store);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public Store updateStore(StoreDto s) {
		// TODO Auto-generated method stub
//		Store st = storeRepository.getById(s.getId());
//		if(null == st) {
//			throw new AppException(402,"Store is not Found!");
//		}
//		st.setClose_time(s.getClose_time());
//		st.setDescription(s.getDescription());
//		st.setOpen_time(s.getOpen_time());
//		st.setStatus(s.getStatus());
//		st.setLocation(s.getLocation());
//		st.setStore_category(st.getStore_category());
//		
//		return storeRepository.save(st);
		Store st = storeRepository.getById(s.getId());
		if (null == st) {
			throw new AppException(402, "Store is not Found!");
		}
		if (s.getAddress().getId() == 0) {
			throw new AppException(402, "Id of Address is not Found!");
		}
		s.getStoreDetail().forEach((sct) -> {
			if(sct.getId() == 0) {
				throw new AppException(402, "Id of Category is not Found!");
			}
		});

		//BeanUtils.copyProperties(rp, reliefPoint);
		Store storeTemp = mapStructMapper.storeDtoToStore(s);
		List<StoreCategoryDto> lstStoreDetailDto = s.getStoreDetail();
		List<StoreCategory> lstStoreDetail = lstStoreDetailDto.stream().map(storeDetailDto -> {
			StoreCategory storeDetail = new StoreCategory();
			storeDetail.setId(storeDetailDto.getId());
			storeDetail.setName(storeDetailDto.getName());
			return storeDetail;
		}).collect(Collectors.toList());
		storeTemp.setStore_category(lstStoreDetail);
		Address address = addressService.mapAddress(s.getAddress());
		storeTemp.setLocation(address);
		storeTemp.setClose_time(DateUtils.stringToTimeHHMM(s.getOpen_time()));
		storeTemp.setDescription(s.getDescription());
		storeTemp.setOpen_time(DateUtils.stringToTimeHHMM(s.getOpen_time()));
		storeTemp.setStatus(s.getStatus());
		
		return storeRepository.saveAndFlush(storeTemp);
	}

	@Override
	public Store openCloseStore(StoreDto s) {
		// TODO Auto-generated method stub
		Store st = storeRepository.getById(s.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		st.setStatus(s.getStatus());
		notificationService.sendPnsToDeviceSubcribeStore(st, "Cửa hàng đã mở cửa");
		return storeRepository.save(st);
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
	public List<StoreDto> getStoreFilterByType(long user_id, int status, String type, int page_size, int page_index) {
		//Object[] stm = storeRepository.filterStoreByStatusType(1, "yess");
		List<Object[]> lsRs = storeRepository.getStoreByStatusOrType(user_id, status, type,page_index,page_size);
		return mapStructMapper.lstStoreToStoreDto(mapper.getStoreByStatusOrType_Mapper(lsRs));
	}
}
