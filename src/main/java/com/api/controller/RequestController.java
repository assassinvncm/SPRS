package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.Group;
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
	public ResponseEntity<?> getRequestOrgAdmin(@RequestHeader("Authorization") String requestTokenHeader) {
		List<Request> requests = null;
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);

		requests = requestService.getRequestbyOrganization(user.getOrganization().getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requests, null));
	}

	@RequestMapping("/request-systemAdmin")
	public ResponseEntity<?> getRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader) {
		List<Request> requests = null;
		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);
		//user.getGroups_user();
		requests = requestService.getRequestbySysAdmin(user.getGroups_user().get(0).getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requests, null));
	}

	@RequestMapping(value = "/admin/register/accept", method = RequestMethod.PUT)
	public ResponseEntity<?> acceptRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody List<Long> rId) {

		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);

		requestService.acceptRequest(rId,user.getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Accept Request success!", "", null, null));
	}

	@RequestMapping(value = "/admin/register/reject", method = RequestMethod.PUT)
	public ResponseEntity<?> rejectRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody List<Long> rId) {

		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);

		requestService.RejectRequest(rId,user.getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", null, null));
	}

	@RequestMapping(value = "/request-systemAdmin", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody Request request) {

		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);

		Request req = requestService.handleRequest(request);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", req, null));
	}

	@RequestMapping(value = "/request-organizationalAdmin", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequestOrgAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody Request request) {

		String jwtToken = requestTokenHeader.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		User user = userService.findByUsername(username);

		Request req = requestService.handleRequest(request);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", req, null));
	}

}
