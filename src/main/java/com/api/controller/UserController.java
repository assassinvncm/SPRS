package com.api.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.User;
import com.api.repositories.AcceptanceRepository;
import com.api.repositories.GroupRepository;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/sprs/api")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService; 
	
	@Autowired
	GroupRepository groupServ;
	
	@Autowired
	AcceptanceRepository accServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		List<User> lst = userService.getAllUser();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, lst));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getUserbyToken(@RequestHeader ("Authorization") String requestTokenHeader){

		logger.info("Start get User");

		UserDto useDto = userService.getUserbyToken(requestTokenHeader);
		logger.info("End get User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", useDto, null));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
		userService.registerUser(user);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/user", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser_v2(@Validated @RequestBody UserDto userDto) {
		userService.registerUser_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/organizationlAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerOrganization_v2(@Validated @RequestBody UserDto userDto) {
		 userService.registerOrganization_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/organizationalUser", method = RequestMethod.POST)
	public ResponseEntity<?> registerOrganizationalUser_v2(@Validated @RequestBody UserDto userDto) {
		userService.registerOrganizationUser_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/ownStore", method = RequestMethod.POST)
	public ResponseEntity<?> registerOownStore_v2(@Validated @RequestBody UserDto userDto) {
		userService.registerStoreUser_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id, @Validated @RequestBody User bean){
		logger.info("Start update User id: "+id);
		User user = userService.getOne(id);
		if(user == null) {
			ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(bean, user);

		logger.info("End update User id: "+id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update user success!", "", null, null));
	}
	
	
}
