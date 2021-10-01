package com.api.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Group;
import com.api.model.SPRSResponse;
import com.api.model.User;
import com.api.services.GroupServices;
import com.api.services.UserServices;

@RestController
@RequestMapping("/sprs/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServices userService; 
	
	@Autowired
	GroupServices groupServ;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser(@Validated @RequestBody User bean) {
		logger.info("Start save User");
		User u = userService.findByUsername(bean.getUsername());
		logger.info("Start save Success");
		return userService.save(bean);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@Validated @RequestBody User bean) {
		logger.info("Start save User");
		User u = userService.findByUsername(bean.getUsername());
		if(u!=null) {
			return ResponseEntity.ok(new SPRSResponse("201", "", "Username is existed!"));
		}else {
			Collection<Group> lstTem = bean.getGroups_user();
			for (Group group : lstTem) {
				Optional<Group> grTemp = groupServ.findById(group.getId());
				if(grTemp.isEmpty()) {
					return ResponseEntity.ok(new SPRSResponse("202","","Group not Found!"));
				}else {
					userService.save(bean);
				}
			}
		}
		logger.info("End save User");
		return ResponseEntity.ok(new SPRSResponse("101", "Create user success!", ""));
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
