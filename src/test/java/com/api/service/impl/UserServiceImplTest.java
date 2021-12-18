package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.entity.Group;
import com.api.entity.Permission;
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
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(u);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		
		// Call method

		List<User> ucheck = userServ.getAllUser();
		
	    //compare
	    assertEquals(1,ucheck.get(0).getId());
		
	}

}
