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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.Group;
import com.api.entity.Permission;
import com.api.repositories.GroupRepository;
import com.api.repositories.PermissionRepository;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupController {
	
	public static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	GroupRepository groupServ;
	
	@Autowired
	PermissionRepository perRepo;
	
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ResponseEntity<?> listAllContact(){
		logger.info("Start get all Group");
		List<Group> listGroup= groupServ.findAll();
		if(listGroup.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND, "", "Group is not existed!", null, null));
		}
		logger.info("End get all Group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, listGroup));
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start get Group by id: "+id);
		Optional<Group> gr = groupServ.findById(id);
		if(gr.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND, "", "Group is not existed!", null, null));
		}
		logger.info("End get Group by id: "+id);
	    return new ResponseEntity<>(new SPRSResponse(Constants.SUCCESS, "", "", gr, null), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public ResponseEntity<?> saveGroup(@Validated @RequestBody Group bean) {
		logger.info("Start save Group");
		Group gr = groupServ.findByName(bean.getName());
		if(gr!=null) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND, "", "Group is existed!", null, null));
		}else {
			groupServ.save(bean);
		}
		logger.info("End save Group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create group success!", "", null, null));
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGroup(@PathVariable(value = "id") Long id, @Validated @RequestBody Group bean){
		logger.info("Start update Group id: "+id);
		Optional<Group> gr = groupServ.findById(id);
		if(gr.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND, "", "Group is not existed!", null, null));
		}
		
		BeanUtils.copyProperties(bean, gr);

		logger.info("End update Group id: "+id);
		return ResponseEntity.ok(groupServ.save(gr.get()));
	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start delete Group id: "+id);
		Optional<Group> gr = groupServ.findById(id);
		if(gr.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND, "", "Group is not existed!", null, null));
		}
		groupServ.deleteById(gr.get().getId());
		logger.info("End delete Group id: "+id);
	    return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<?> grantPermission(@Validated @RequestBody Group group){
		logger.info("Start grant permission!");
		Optional<Group> g = groupServ.findById(group.getId());
		if(g.isEmpty()) {
			return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND,"","Group not Found!", null, null));
		}else {
			Collection<Permission> lstTem = group.getPermissions();
			for (Permission permis : lstTem) {
				Optional<Permission> perTemp = perRepo.findById(permis.getId());
				if(perTemp.isEmpty()) {
					return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND,"","Permission not Found!", null, null));
				}else {
					groupServ.save(group);
				}
			}
		}
		logger.info("End grant permission!");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Grant permission success!", "", null, null));
	}
}
