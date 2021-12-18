package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.dto.AddressDto;
import com.api.dto.GroupDto;
import com.api.dto.OrganizationDto;
import com.api.dto.StoreDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.Permission;
import com.api.entity.Store;
import com.api.entity.SubDistrict;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.repositories.StoreRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.SOSService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	StoreRepository storeRepo;

	@Mock
	GroupRepository groupRepository;

	@Mock
	RequestRepository requestRepository;

	@Mock
	OrganizationRepository organizationRepository;

	@Mock
	AddressService addressService;

	@Mock
	ModelMapper modelMapper;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JwtTokenUtil jwtTokenUtil;
	
	@Mock
	MapStructMapper mapStructMapper;
	
	@Mock
	SOSService sosServ;
	
	@InjectMocks
	UserService userServ = new UserSerivceImpl();
	
	@Test
	public void testGetAllUser_UTCID01() {
		
		// Set data test
		User u = new User();
		u.setId(1);
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(u);
		// Mock
		Mockito.when(userRepository.findAll()).thenReturn(lstUser);
		
		// Call method

		List<User> ucheck = userServ.getAllUser();
		
	    //compare
	    assertEquals(1,ucheck.get(0).getId());
		
	}
	
	@Test
	public void getUserbyToken_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		Mockito.when(mapStructMapper.userToUserDto(u)).thenReturn(new UserDto());
		Mockito.when(mapStructMapper.lstGroupToGroupDto(new ArrayList<Group>())).thenReturn(new ArrayList<GroupDto>());
		Mockito.when(mapStructMapper.addressToAddressDto(new Address())).thenReturn(new AddressDto());
		Mockito.when(mapStructMapper.organizationToOrganizationDto(new Organization())).thenReturn(new OrganizationDto());
		
		// Call method

		UserDto ucheck = userServ.getUserbyToken(token);
		
	    // Compare
	    assertEquals(0,ucheck.getId());
	}
	
	@Test
	public void getUserbyToken_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUserbyToken(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getNativeUserbyToken_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		Mockito.when(userRepository.getById(u.getId())).thenReturn(u);
		
		// Call method

		User ucheck = userServ.getNativeUserbyToken(token);
		
	    // Compare
	    assertEquals(1,ucheck.getId());
	}
	
	@Test
	public void getNativeUserbyToken_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getNativeUserbyToken(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getUserbyTokenAuth_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		
		// Call method

		User ucheck = userServ.getUserbyTokenAuth(token);
		
	    // Compare
	    assertEquals(1,ucheck.getId());
	}
	
	@Test
	public void getUserbyTokenAuth_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUserbyTokenAuth(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(new Group());
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		// Mock
		Mockito.when(userRepository.findByUsername("duongpt")).thenReturn(null);
		Mockito.when(userRepository.findByPhone(u.getPhone())).thenReturn(Optional.empty());
		Mockito.when(groupRepository.findById(gid)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(new Group());
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		// Mock
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		Mockito.when(userRepository.findByPhone(u.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Group is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(new Group());
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setUsername("duongpt");
		// Mock
		Mockito.when(userRepository.findByUsername(u.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganization_v2_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganization_v2(udto);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganization_v2_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganization_v2(udto);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
}
