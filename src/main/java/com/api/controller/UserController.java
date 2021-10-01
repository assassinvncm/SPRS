package com.api.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Group;
import com.api.model.SPRSResponse;
import com.api.model.User;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userService; 
	
	@Autowired
	GroupRepository groupServ;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		logger.info("Start get all User");
		List<User> lst = null;
		try {
			lst = userService.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage()));
		}
		logger.info("End get all User");
		return new ResponseEntity<List<User>>(lst, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id){
		logger.info("Start User by id: "+id);
		Optional<User> user = null;
		try {
			user = userService.findById(id);
		} catch (Exception e) {
			logger.info("Error get User by id: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage()));
		}
		logger.info("End get User by id: "+id);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@Validated @RequestBody User bean) {
		logger.info("Start save User");
		User u = userService.findByUsername(bean.getUsername());
		if(u!=null) {
			return ResponseEntity.ok(new SPRSResponse(Constants.EXISTED, "", "Username is existed!"));
		}else {
			Collection<Group> lstTem = bean.getGroups_user();
			for (Group group : lstTem) {
				Optional<Group> grTemp = groupServ.findById(group.getId());
				if(grTemp.isEmpty()) {
					return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND,"","Group not Found!"));
				}else {
					userService.save(bean);
				}
			}
		}
		logger.info("End save User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", ""));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long id, @Validated @RequestBody User bean){
		logger.info("Start update User id: "+id);
		User user = userService.getOne(id);
		if(user == null) {
			ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(bean, user);

		logger.info("End update User id: "+id);
		return ResponseEntity.ok(userService.save(user));
	}
}
