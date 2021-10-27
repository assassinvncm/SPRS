package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import javax.persistence.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.controller.UserController;
import com.api.dto.GroupDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
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
	RequestRepository requestRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	AddressService addressService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	MapStructMapper mapStructMapper;

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
	public UserDto getUserbyToken(String requestTokenHeader) {
		// TODO Auto-generated method stub
		logger.info("Start get User");

		String username = jwtTokenUtil.getUserNameByToken(requestTokenHeader);

		User user = Optional.ofNullable(userRepository.findByUsername(username))
				.orElseThrow(() -> new AppException(501, "Error when query to get user"));
		logger.info("End get User");
		
		//mapper
		UserDto userDto= mapStructMapper.userToUserDto(user);
		return userDto;
	}

	@Transactional
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(user.getUsername());
		boolean checkGr = true;
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		userRepository.findByPhone(user.getPhone()).orElseThrow(() -> new AppException(403, "Phone is exsit!"));

		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
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
			//createRequestAccept("Account accepting", "Account accepting", user);
		}
		logger.info("End save User");
		return user;
	}

	@Transactional
	@Override
	public void registerUser_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		// User u = userRepository.getByPhone(user.getPhone());

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);
		user.setIsActive(true);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		logger.info("End save User");
		// return user;
	}

	@Transactional
	@Override
	public void registerOrganization_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save Organization");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);
		user.setIsActive(false);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// user.setGroups_user(lstTem);G
		Request req = createRequestRegister("request to register", null, user);

		userRepository.save(user);
		logger.info("End save Organization");
		logger.info("Start save Request");
		requestRepository.save(req);
		logger.info("End save Request");
		// return user;
	}

	@Transactional
	@Override
	public void registerOrganizationUser_v2(UserDto userDto) {
		logger.info("Start save Organizational User");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}

		Organization organization = organizationRepository.findById(userDto.getOrganization().getId())
				.orElseThrow(() -> new AppException(403, "organization is not exist!"));
		// chưa check admin organization phải tồn tại

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);
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
		// return user;
	}

	@Override
	public void registerStoreUser_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save Own Store");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);

		user.setIsActive(false);
		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// user.setGroups_user(lstTem);G
		Request req = createRequestRegister("request to create store", "Create Store", user);

		userRepository.save(user);
		logger.info("End save Organization");
		logger.info("Start save Request");
		requestRepository.save(req);
		logger.info("End save Request");
		// return user;
	}

	private void checkRoleAndUser() {

	}

//	public boolean checkRq_v2(User user) {
//		
//	}

	public Request createRequestRegister(String message, String type, User u) {
		logger.info("Start create request type: ");
		Request req = new Request();
		req.setUser(u);
		req.setMessage(message);
		req.setType(type);
		req.setStatus(Constants.REQUEST_STATUS_UNCHECK);
		req.setTimestamp(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		// đang hardcode (nên xem, check quyền tạo)
		if (u.getGroups_user().get(0).getId() == 3) {
			req.setOrganization(u.getOrganization());
		} else {
			Group g = new Group();
			// sai set id. ID phải là id của admin
			g.setId(10);
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

	@SuppressWarnings("null")
	@Override
	public String getUsernameByPhone(String phone) {
		if(phone !=null || !phone.equals("")) {
			phone = "0"+phone.substring(3);
			Optional<User> u = userRepository.findByPhone(phone);
			if(!u.isEmpty()) {
				return u.get().getUsername();
			}
		}else {
			throw new AppException(404, "Phone number not Found");
		}
		return null;
	}

	@Override
	public void updatePassword(UserDto userDto, String newPassword) {
		// TODO Auto-generated method stub
		User user = mapStructMapper.userDtoToUser(userDto);
		user.setPassword(newPassword);
		userRepository.save(user);
	}

	@Override
	public void updateUser(UserDto userDto,UserDto bean) {
		// TODO Auto-generated method stub
		userDto.setFull_name(bean.getFull_name());
		userDto.setAddress(bean.getAddress());
		userDto.setDob(bean.getDob());
		userDto.setOrganization(bean.getOrganization());
		userDto.setOrganization(null);
		
		User user = mapStructMapper.userDtoToUser(userDto);
		userRepository.save(user);
		
	}

}
