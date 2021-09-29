package com.api.controller;

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
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start get Group by id: "+id);
		Optional<Group> gr = groupServ.findById(id);
		if(gr.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse("203", "", "Group is not existed!"));
		}
		logger.info("End get Group by id: "+id);
	    return new ResponseEntity<>(gr, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public ResponseEntity<?> saveGroup(@Validated @RequestBody Group bean) {
		logger.info("Start save Group");
		Group gr = groupServ.findByName(bean.getName());
		if(gr!=null) {
			return ResponseEntity.ok(new SPRSResponse("203", "", "Group is existed!"));
		}else {
			groupServ.save(bean);
		}
		logger.info("End save Group");
		return ResponseEntity.ok(new SPRSResponse("101", "Create group success!", ""));
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGroup(@PathVariable(value = "id") Long id, @Validated @RequestBody Group bean){
		logger.info("Start update Group id: "+id);
		Optional<Group> gr = groupServ.findById(id);
		if(gr.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse("203", "", "Group is not existed!"));
		}
		
		BeanUtils.copyProperties(bean, gr);

		logger.info("End update Group id: "+id);
		return ResponseEntity.ok(groupServ.save(gr.get()));
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start delete Group id: "+id);
		Group gr = groupServ.getOne(id);
		if(gr == null) {
			return ResponseEntity.ok(new SPRSResponse("203", "", "Group is not existed!"));
		}
		groupServ.delete(gr);
		logger.info("End delete Group id: "+id);
	    return ResponseEntity.ok().build();
	}
}
