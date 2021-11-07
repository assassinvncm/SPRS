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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.api.service.ReliefPointService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("sprs/api/reliefPoint-manage")
public class RefliefPointController {

	public static Logger logger = LoggerFactory.getLogger(RefliefPointController.class);

	@Autowired
	ReliefPointService reliefPointService;

	@Autowired
	UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReliefPoint(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody ReliefPointDto reliefPointDto) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		reliefPointDto.setUser_rp(userDto);

		ReliefPoint rp = reliefPointService.createReliefPoint(reliefPointDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPoint() {
		List<ReliefPointDto> lstReliefPoint = reliefPointService.getAllReliefPoint();
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get Relief Point by area success", "", lstReliefPoint, null));
	}

	/**
	 * get relief point by Id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reliefPoint", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPointById(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam(name = "id") long rpId) {
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		ReliefPointDto rpDto = reliefPointService.getReliefPointByIdAndUser(rpId, userDto.getId());
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get Relief Point by area success", "", rpDto, null));
	}


	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPointByCity(@RequestParam(name = "") long id) {
		List<ReliefPointDto> rpDto = reliefPointService.getReliefPointByArea(null);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get Relief Point by area success", "", rpDto, null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateReliefPoint(@RequestBody Object reliefPoint) {

	}

}
