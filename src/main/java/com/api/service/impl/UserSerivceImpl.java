package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.controller.UserController;
import com.api.dto.SPRSResponse;
import com.api.entity.Acceptance;
import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.entity.User;
import com.api.repositories.AcceptanceRepository;
import com.api.repositories.GroupRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.repositories.UserRepository;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;
import com.ultils.Ultilities;

@Service
public class UserSerivceImpl implements UserService {

	public static Logger logger = LoggerFactory.getLogger(UserSerivceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	AcceptanceRepository accRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User save(User userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		logger.info("Start get all User");
		List<User> lst = null;
		try {
			lst = userRepository.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: " + e.getMessage());
			throw new AppException(501, "Error when get user");
		}
		logger.info("End get all User");
		return lst;
	}

	@Override
	public User getUserbyToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(user.getUsername());
		boolean checkGr = true;
		if (u != null) {
			// return ResponseEntity.ok(new SPRSResponse(Constants.EXISTED, "", "Username is
			// existed!", null, null));
			throw new AppException(403, "Username is existed!");
		}
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				// return ResponseEntity.ok(new SPRSResponse(Constants.NOTFOUND,"","Group not
				// Found!", null, null));
				throw new AppException(403, "Group is not exist!");
			}
			if (group.getLevel() == 0) {
				checkGr = false;
			}
		}
		user.setIsActive(checkGr);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		if (checkRqUser(user)) {
			createRequestAccept("Account accepting", "Account accepting", user);
		}
		logger.info("End save User");
		return user;
	}

	@Transactional
	@Override
	public User registerUser_v2(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(user.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		user.setIsActive(true);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		logger.info("End save User");
		return user;
	}

	@Transactional
	@Override
	public User registerOrganization_v2(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save Organization");
		User u = userRepository.findByUsername(user.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		user.setIsActive(false);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//user.setGroups_user(lstTem);G
		Request req = createRequestRegister("request to register", null, user);
		
		userRepository.save(user);
		logger.info("End save Organization");
		logger.info("Start save Request");
		requestRepository.save(req);
		logger.info("End save Request");
		return user;
	}
	
	@Transactional
	@Override
	public User registerOrganizationUser_v2(User user) {
		logger.info("Start save Organizational User");
		User u = userRepository.findByUsername(user.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		
		Organization organization =  organizationRepository.findById(user.getOrganization().getId()).orElseThrow(() ->  new AppException(403, "organization is not exist!"));
		//chưa check admin organization phải tồn tại
		
		user.setOrganization(organization);
		
		user.setIsActive(false);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		logger.info("End save Organization");
		logger.info("Start save request");
		Request req = createRequestRegister("request to register", null, user);
		requestRepository.save(req);
		logger.info("End save Request");
		return user;
	}
	
	@Override
	public User registerStoreUser_v2(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save Organizational User");
		User u = userRepository.findByUsername(user.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		
		
		return null;
	}
	
	
	private void checkRoleAndUser() {
		
	}

//	public boolean checkRq_v2(User user) {
//		
//	}
	
	public Request createRequestRegister(String message,String type,User u) {
		logger.info("Start create request type: ");
		Request req = new Request();
		req.setUser(u);
		req.setMessage(message);
		req.setType(type);
		req.setStatus("uncheck");
		req.setTimestamp(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		if(u.getGroups_user().get(0).getId()==2) {
			req.setOrganization(u.getOrganization());
		}else {
			Group g = new Group();
			g.setId(4);
			req.setGroup(g);
		}
		
		return req;
	}

	public boolean checkRqUser(User user) {
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				return false;
			} else {
				if (group.getLevel() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public void createRequestAccept(String request_name, String type, User u) {
		logger.info("Start create request type: " + type);
		Acceptance a = new Acceptance();
		a.setRequest_name(request_name);
		a.setType(type);
		a.setRequest_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		a.setStatus(false);
		a.setUser(u);
		accRepository.save(a);
		logger.info("End create request type: " + type);
	}



}
