package com.api.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Group;
import com.api.model.SPRSResponse;
import com.api.model.User;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;
import com.ultils.Ultilities;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/sprs/api")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userService; 
	
	@Autowired
	GroupRepository groupServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		logger.info("Start get all User");
		List<User> lst = null;
		try {
			lst = userService.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage(), null, null));
		}
		logger.info("End get all User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, lst));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@RequestHeader ("Authorization") String requestTokenHeader){
//		String requestTokenHeader = request.getHeader("Authorization");
		logger.info("Start get User");

		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"","Unable to get JWT Token", null, null));
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"","JWT Token has expired", null, null));
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"","JWT Token does not begin with Bearer String", null, null));
		}
		
		Optional<User> user = null;
		try {
			user = Optional.ofNullable(userService.findByUsername(username));
		} catch (Exception e) {
			logger.info("Error get User: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage(), null, null));
		}
		logger.info("End get User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", user.get(), null));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@Validated @RequestBody User user) {
		logger.info("Start save User");
		User u = userService.findByUsername(user.getUsername());
		boolean checkGr = true;
		if(u!=null) {
			return ResponseEntity.ok(new SPRSResponse(Constants.EXISTED, "", "Username is existed!", null, null));
		}else {
			List<Group> lstTem = user.getGroups_user();
			for (Group group : lstTem) {
				Optional<Group> grTemp = groupServ.findById(group.getId());
				if(grTemp.isEmpty()) {
					return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND,"","Group not Found!", null, null));
				}else {
					if(group.getLevel() == 1) {
						checkGr = false;
					}
				}
			}
			user.setIsActive(checkGr);
			user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
			userService.save(user);
		}
		logger.info("End save User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Long id, @Validated @RequestBody User bean){
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
