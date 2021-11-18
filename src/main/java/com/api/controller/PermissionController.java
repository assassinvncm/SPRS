package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.Permission;
import com.api.entity.User;
import com.api.repositories.PermissionRepository;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
public class PermissionController {
	public static Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	PermissionRepository perRepo;
	
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPermission(){
		logger.info("Start get all Permission");
		List<Permission> lst = null;
		try {
			lst = perRepo.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage(), null, null));
		}
		logger.info("End get all Permission");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, lst));
	}
	
	@RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPermissionById(@PathVariable(value = "id") Long id){
		logger.info("Start Permission by id: "+id);
		Optional<Permission> per = null;
		try {
			per = perRepo.findById(id);
		} catch (Exception e) {
			logger.info("Error get Permission by id: "+e.getMessage());
			return ResponseEntity.ok(new SPRSResponse(Constants.SERVER_ERR,"",e.getMessage(), null, null));
		}
		logger.info("End get User by id: "+id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", per.get(), null));
	}
	
//	@RequestMapping(value = "/permission", method = RequestMethod.POST)
//	public ResponseEntity<?> savePermission(@Validated @RequestBody Permission per){
//		logger.info("Start save permission id: "+per.getId());
//		Optional<Permission> perm = perRepo.findById(per.getId());
//		if(perm.isEmpty()) {
//			perRepo.save(per);
//		}else {
//			return ResponseEntity.ok(new SPRSResponse(Constants.EXISTED,"","Permission is existed!", null, null));
//		}
//		logger.info("End save permission id: "+per.getId());
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
//	}
	
	@RequestMapping(value = "/permission/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePermission(@PathVariable(value = "id") Long id ,@Validated @RequestBody Permission per){
		logger.info("Start update permission id: "+per.getId());
		Optional<Permission> perm = perRepo.findById(per.getId());
		if(perm.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.EXISTED,"","Permission is existed!", null, null));
		}
		perRepo.save(per);
		logger.info("End update permission id: "+per.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
	}
}
