package com.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.User;
import com.api.services.UserServices;

@RestController
@RequestMapping("/sprs/api")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserServices userService; 
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUser(@Validated @RequestBody User bean) {
		logger.info("Start save User");
		User u = userService.findByUsername(bean.getUsername());
		logger.info("Start save Success");
		return userService.save(bean);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User saveEmployee(@Validated @RequestBody User bean) {
		logger.info("Start save User");
		User u = userService.findByUsername(bean.getUsername());
		logger.info("Start save Success");
		return userService.save(bean);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long id, @Validated @RequestBody User bean){
		logger.info("Start update User id: "+id);
		User employee = userService.getOne(id);
		if(employee == null) {
			ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(bean, employee);

		logger.info("End update User id: "+id);
		return ResponseEntity.ok(userService.save(employee));
	}
}
