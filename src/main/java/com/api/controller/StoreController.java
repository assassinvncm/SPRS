package com.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
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
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReliefPoint(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody Store s) {
		
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				throw new AppException(501,"Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				throw new AppException(501,"JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			throw new AppException(501,"JWT Token does not begin with Bearer String");
		}
		
		User user = userSerivce.findByUsername(username);
		s.setUsers(user);
		
		Store rp = storeService.createStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", rp, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void getStore() {

	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public void getStoreById(@PathVariable(value = "id") Long id) {

	}

//	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//	public void getReliefPointByArea(@PathVariable(value = "id") String id ) {
//		
//	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateStore(@RequestBody Object reliefPoint) {

	}
}
