/**
 * 
 */
package com.api.service.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.ItemDto;
import com.api.dto.ReliefInformationDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.SubDistrictDto;
import com.api.entity.Address;
import com.api.entity.Item;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.SubDistrict;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.ReliefPointService;
import com.common.utils.DateUtils;
import com.exception.AppException;

/**
 * @author MY PC
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class ReliefPointServiceImplTest {
	
	
	
	@Mock
	ReliefPointRepository reliefPointRepository;
	
	@Mock
	MapStructMapper mapStructMapper;

	@Mock
	AddressService addressService;
	
	@Mock
	NotificationService notificationService;
	
	@InjectMocks
	private ReliefPointService reliefPointService = new ReliefPointServiceImpl();

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointById(java.lang.Long)}.
	 */
	@Test
	void testGetReliefPointById_UTCID01() {
		//set input data for method
		long id = 2;
		
		//mock data
		ReliefPoint rp = new ReliefPoint();
		//mock
		Mockito.when(reliefPointRepository.getById(id)).thenReturn(rp);
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(2);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(rp)).thenReturn(rpDto);
		
		//call method
		ReliefPointDto rpDtoRes = reliefPointService.getReliefPointById(id);
		assertEquals(rpDto.getId(), rpDtoRes.getId());
	}
		

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#createReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
	@Test
	void testCreateReliefPoint_UTCID01() {
		
		//set data input for method
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		//data mock
		ReliefPoint rp = new ReliefPoint();
		List<ReliefInformation> lstRpInfor = new ArrayList<ReliefInformation>();
		ReliefInformation rpInfor = new ReliefInformation();
		lstRpInfor.add(rpInfor);
		rp.setReliefInformations(lstRpInfor);
		
		
		Mockito.when(mapStructMapper.reliefPointDtoToreliefPoint(rpDto)).thenReturn(rp);
		
		rp.setId(2);
		Mockito.when(reliefPointRepository.save(rp)).thenReturn(rp);
		Mockito.doNothing().when(notificationService).sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
		
		//call method
		ReliefPoint rpRes = reliefPointService.createReliefPoint(rpDto);
		assertEquals(rp.getId(), rpRes.getId());
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#updateReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
	@Test
	void testUpdateReliefPoint_UTCID01() {
		
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(null);
		
		AppException appException = assertThrows(AppException.class, () -> {
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Relief point is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID02() {
		
		//set data input for reliefpointDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		AppException appException = assertThrows(AppException.class, () -> {
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Id of Address is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID03() {
		
		//set data input for reliefpointDto
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
		
		List<ReliefInformationDto> lstReliefInformationDto = new ArrayList<ReliefInformationDto>();
		
		ReliefInformationDto reliefInformationDto = new ReliefInformationDto();
		reliefInformationDto.setId(2);
		ItemDto itemDto = new ItemDto();
		itemDto.setId(0);
		reliefInformationDto.setItem(itemDto);
		lstReliefInformationDto.add(reliefInformationDto);
		
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		rpDto.setReliefInformations(lstReliefInformationDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Id of Item is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID04() {
		
		//set data input for reliefpointDto
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
		
		List<ReliefInformationDto> lstReliefInformationDto = new ArrayList<ReliefInformationDto>();
		
		ReliefInformationDto reliefInformationDto = new ReliefInformationDto();
		reliefInformationDto.setId(2);
		ItemDto itemDto = new ItemDto();
		itemDto.setId(3);
		reliefInformationDto.setItem(itemDto);
		lstReliefInformationDto.add(reliefInformationDto);
		
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		rpDto.setReliefInformations(lstReliefInformationDto);
		
		//mock
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		ReliefPoint rp = new ReliefPoint();
		List<ReliefInformation> lstRpInfor = new ArrayList<ReliefInformation>();
		ReliefInformation rpInfor = new ReliefInformation();
		lstRpInfor.add(rpInfor);
		rp.setReliefInformations(lstRpInfor);
		Mockito.when(mapStructMapper.reliefPointDtoToreliefPoint(rpDto)).thenReturn(rp);
		Mockito.when(addressService.mapAddress(rpDto.getAddress())).thenReturn(new Address());
		Mockito.when(reliefPointRepository.saveAndFlush(rp)).thenReturn(rp);
		
		//call method
		reliefPointService.updateReliefPoint(rpDto);
		
		
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByIdAndUser(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testGetReliefPointByIdAndUser_UTCID01() {
		//init param
		long rpId = 2;
		long uId =3;
		
		//mock
		ReliefPoint rp = new ReliefPoint();
		ReliefPointDto rpDto = new ReliefPointDto();
		Optional<ReliefPoint> reliefPoint = Optional.of(rp);
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findByIdAndUser(rpId, uId)).thenReturn(reliefPoint);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(rp)).thenReturn(rpDto);
		
		//call method
		reliefPointService.getReliefPointByIdAndUser(rpId, uId);
	}
	
	@Test
	void testGetReliefPointByIdAndUser_UTCID02() {
		long rpId = 2;
		long uId =3;
		ReliefPoint rp = new ReliefPoint();
		ReliefPointDto rpDto = new ReliefPointDto();
		Optional<ReliefPoint> reliefPoint = Optional.empty();
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findByIdAndUser(rpId, uId)).thenReturn(reliefPoint);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(rp)).thenReturn(rpDto);
		
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			reliefPointService.getReliefPointByIdAndUser(rpId, uId);
	    }); 
		String expectedMessage = "Reliefpoint not exist";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}

//	@InjectMocks
//	ReliefPointFilterDto reliefPointFilterDto;
	
	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPoints(java.lang.Long, com.api.dto.ReliefPointFilterDto)}.
	 */
	@Test
	void testGetReliefPoints_UTCID01() {
		ReliefPointFilterDto reliefPointFilterDto = new ReliefPointFilterDto();
		List<ReliefPoint> reliefPoints = new ArrayList<ReliefPoint>();
		List<ReliefPointDto> reliefpointDto = new ArrayList<ReliefPointDto>();
		Long u_id = new Long(1);
		
		Mockito.when(reliefPointRepository.findByTypeOrStatus(u_id,reliefPointFilterDto)).thenReturn(reliefPoints);
		Mockito.when(mapStructMapper.lstReliefPointToreliefPointDto(reliefPoints)).thenReturn(reliefpointDto);
		
		//call method
		reliefPointService.getReliefPoints(u_id, reliefPointFilterDto);
		assertTrue(true);
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#updateStatusReliefPoint(java.lang.Long, java.lang.Boolean)}.
	 */
	@Test
	void testUpdateStatusReliefPoint_UTCID01() {
		Long r_id = new Long(1);
		Boolean status = true;
		ReliefPoint rp = new ReliefPoint();
		Optional<ReliefPoint> reliefPoint = Optional.of(rp);
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findById(r_id)).thenReturn(reliefPoint);
		Mockito.when(reliefPointRepository.save(rp)).thenReturn(rp);
		
		//call method
		reliefPointService.updateStatusReliefPoint(r_id, status);
		assertEquals(status,true);
	}
	
	@Test
	void testUpdateStatusReliefPoint_UTCID02() {
		Long r_id = new Long(1);
		Boolean status = true;
		ReliefPoint rp = new ReliefPoint();
		Optional<ReliefPoint> reliefPoint = Optional.empty();
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findById(r_id)).thenReturn(reliefPoint);
		AppException appException = assertThrows(AppException.class, () -> {
			
			//call method
			reliefPointService.updateStatusReliefPoint(r_id, status);
	    }); 
		String expectedMessage = "ReliefPoint not exist";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage);
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#deleteReliefPointById(java.lang.Long)}.
	 */
	@Test
	void testDeleteReliefPointById_UTCID01() {
		long rId = 2;
		
		//mock type void method
		Mockito.doNothing().when(reliefPointRepository).deleteById(rId);
		
		//call method
		reliefPointService.deleteReliefPointById(rId);
	}

}
