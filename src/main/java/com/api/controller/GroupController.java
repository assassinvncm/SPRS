package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Group;
import com.api.services.GroupServices;

@RestController
@RequestMapping("/sprs/api")
public class GroupController {
	
	public static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	GroupServices groupServ;
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ResponseEntity<List<Group>> listAllContact(){
		logger.info("Start get all Group");
		List<Group> listGroup= groupServ.findAll();
		if(listGroup.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		logger.info("End get all Group");
		return new ResponseEntity<List<Group>>(listGroup, HttpStatus.OK);
	}
}
