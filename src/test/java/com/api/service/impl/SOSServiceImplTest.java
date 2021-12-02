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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SOSDto;
import com.api.dto.SubDistrictDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.SOS;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.SOSRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.SOSService;
import com.exception.AppException;


/**
 * @author MY PC
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SOSServiceImplTest {

	@Mock
	SOSRepository sosRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	MapStructMapper mapStructMapper;

	@Mock
	AddressService addressService;
	
	@InjectMocks
	SOSService sosServ = new SOSServiceImpl();
	
	@Test
	public void testUpdateStatusSOS_UTCID01() {
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(3);
		
		//set data input for SOSDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		
		Mockito.when(userRepo.getById(udto.getId())).thenReturn(null);
		
		AppException appException = assertThrows(AppException.class, () -> {
			sosServ.updateStatusSOS(sdto, udto);
	    }); 
		
		String expectedMessage = "User is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	public void testUpdateStatusSOS_UTCID02() {
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(395);
		
		//set data input for SOSDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);

		//mock
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(1);
		s.setLevel(1);
		Mockito.when(mapStructMapper.SOSDtoToSOS(sdto)).thenReturn(s);
		u.setUser_sos(mapStructMapper.SOSDtoToSOS(sdto));
		Mockito.when(userRepo.getById(udto.getId())).thenReturn(u);
		Mockito.when(addressService.mapAddress(sdto.getAddress())).thenReturn(new Address());
		Mockito.when(sosRepo.save(s)).thenReturn(s);

		//call method
		sosServ.updateStatusSOS(sdto, udto);
	}
	
	@Test
	public void testGetSOSCommon_UTCID01() {
		long id = 395;
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(3);
		
		//set data input for SOSDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(1);
		s.setLevel(1);
		u.setUser_sos(s);
		
		//mock
		Mockito.when(userRepo.getUserBySosId(id)).thenReturn(Optional.empty());
		Mockito.when(mapStructMapper.SOSToSOSDto(s)).thenReturn(sdto);
		Mockito.when(mapStructMapper.addressToAddressDto(new Address())).thenReturn(addressDto);
		
		//call method
		AppException appException = assertThrows(AppException.class, () -> {
			sosServ.getSOSCommon(id);
	    }); 
		
		String expectedMessage = "SOS not exist";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	public void testGetSOSCommon_UTCID02() {
		long id = 395;
		//set data input for SOS
		
		//set data input for SOSDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(1);
		s.setLevel(1);
		u.setUser_sos(s);
		//mock
		Mockito.when(userRepo.getUserBySosId(id)).thenReturn(Optional.of(u));
		Mockito.when(mapStructMapper.SOSToSOSDto(s)).thenReturn(sdto);
		Mockito.when(mapStructMapper.addressToAddressDto(new Address())).thenReturn(addressDto);

		//call method
		sosServ.getSOSCommon(id);
	}

}
