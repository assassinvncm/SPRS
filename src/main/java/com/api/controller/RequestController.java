package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.Request;
import com.api.entity.User;
import com.api.service.RequestService;
import com.api.service.UserService;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/request-manage")
public class RequestController {
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping("/request-organizationalAdmin")
	public ResponseEntity<?> getRequestOr(@RequestHeader("Authorization") String requestTokenHeader){
		List<Request> requests = null;
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);
		
		requests = requestService.getRequestbyOrganization(user.getOrganization().getId());
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requests, null));
	}
}
