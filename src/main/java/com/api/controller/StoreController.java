package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.StoreDto;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.StoreRepository;
import com.api.service.AmazonClient;
import com.api.service.StoreService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("sprs/api/store-manage")
public class StoreController {
	
	public static Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userSerivce;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	private MapStructMapper structMapper;
	
	@Autowired
	private AmazonClient amazonClient;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReliefPoint(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody StoreDto s) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_st(userDto);
		
		Store store = storeService.createStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create store successfully", "", store, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPointById() {
		List<StoreDto> lstStore = storeService.getAllStore();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get All Store success", "", lstStore, null));
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStoreById(@PathVariable(value = "id") Long id) {
		Store st = storeRepository.getById(id);
		StoreDto rs = structMapper.storeToStoreDTO(st);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store By ID "+id+" success", "", rs, null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateStore(@RequestBody StoreDto storeDto) {
		Store s = structMapper.storeDtoToStore(storeDto);
		storeService.updateStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Store By ID "+s.getId()+" success", "", s, null));
	}

	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImg(@RequestPart(value = "file") MultipartFile file, @RequestBody StoreDto storeDto) {
		Store s = structMapper.storeDtoToStore(storeDto);
		String img_url = amazonClient.uploadFile(file);
		storeService.updateStoreImg(s,img_url);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Store By ID "+s.getId()+" success", "", s, null));
	}
}
