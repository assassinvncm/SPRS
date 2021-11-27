package com.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
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
	MapStructMapper mapStruct;

	@Autowired
	UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_STR_ACEN')")
	public ResponseEntity<?> uploadImg(@RequestParam(value = "file") MultipartFile file, String relief_id) {
		logger.info("Start uploadImg Store");
		ReliefPoint rp = reliefPointService.uploadReliefImg(file, relief_id);
		logger.info("End uploadImg Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Upload image for relief point success", "", "", null));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReliefPoint(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody ReliefPointDto reliefPointDto) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		reliefPointDto.setUser_rp(userDto);

		ReliefPoint rp = reliefPointService.createReliefPoint(reliefPointDto);
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<?> getReliefPoint(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody ReliefPointFilterDto rpf) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		List<ReliefPointDto> lstReliefPoint = reliefPointService.getReliefPoints(userDto.getId(), rpf);
		//PagingResponse<ReliefPointDto> lstReliefPoint = reliefPointService.get(userDto.getId(), rpf)
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
	
	/**
	 * get relief point by Id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/common/reliefPoint", method = RequestMethod.GET)
	public ResponseEntity<?> getReliefPointById(@RequestParam(name = "id") long rpId) {
		ReliefPointDto rpDto = reliefPointService.getReliefPointById(rpId);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get Relief Point by area success", "", rpDto, null));
	}

//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	public ResponseEntity<?> getReliefPointByCity(@RequestParam(name = "") long id) {
//		List<ReliefPointDto> rpDto = reliefPointService.getReliefPointByArea(null);
//		return ResponseEntity
//				.ok(new SPRSResponse(Constants.SUCCESS, "Get Relief Point by area success", "", rpDto, null));
//	}

	/**
	 * chưa xong service
	 * 
	 * @param reliefPoint
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateReliefPoint(@RequestBody ReliefPointDto reliefPointDto) {
		ReliefPoint rp = reliefPointService.updateReliefPoint(reliefPointDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS,
				"Update reliefpoint By ID " + rp.getId() + " success", "", rp, null));
	}

	@RequestMapping(value = "/update-status", method = RequestMethod.PUT)
	public ResponseEntity<?> hideReliefPoint(@RequestParam("id") Long id, @RequestParam("status") Boolean status) {
		ReliefPoint ReliefPoint = reliefPointService.updateStatusReliefPoint(id, status);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS,
				"Update status relief point By ID " + ReliefPoint.getId() + " success", "", ReliefPoint, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteReliefPoint(@RequestParam("id") Long id) {

		reliefPointService.deleteReliefPointById(id);
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "delete relief point By ID " + " success", "", null, null));
	}

}
