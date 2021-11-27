/**
 * 
 */
package com.api.service.impl;

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

import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.entity.ReliefPoint;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.ReliefPointService;
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
		fail("Not yet implemented");
	}
	
	@Test
	void testGetReliefPointById_UTCID02() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByUser(com.api.entity.User)}.
	 */
	@Test
	void testGetReliefPointByUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByLocation(com.api.entity.Address)}.
	 */
	@Test
	void testGetReliefPointByLocation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByArea(com.api.dto.AddressDto)}.
	 */
	@Test
	void testGetReliefPointByArea() {
		fail("Not yet implemented");
	}
	
	

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#createReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
//	@Test
//	void testCreateReliefPoint_UTCID01() {
//		@InjectMocks reliefPointDto = Mockito.in;
//		ReliefPoint reliefPoint = new ReliefPoint();
//
//		
//		Mockito.when(mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto)).thenReturn(reliefPoint);
//		
//		Mockito.when(null)
//		
//		
//		ReliefPoint reliefPoint = mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto);
//		List<ReliefInformation> lstRIfor = reliefPoint.getReliefInformations().stream().map(rf -> {
//			rf.setReliefPoint(reliefPoint);
//			return rf;
//		}).collect(Collectors.toList());
//		reliefPoint.setReliefInformations(lstRIfor);
//		Address address = addressService.mapAddress(reliefPointDto.getAddress());
//		reliefPoint.setAddress(address);
//		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
//		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
//		reliefPoint.setStatus(true);
//		reliefPoint.setCreate_time(DateUtils.getCurrentSqlDate());
//		ReliefPoint rp = reliefPointRepository.save(reliefPoint);
//		notificationService.sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
//	}
	
	@Test
	void testCreateReliefPoint_UTCID02() {
		
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#updateReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
	@Test
	void testUpdateReliefPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getAllReliefPoint()}.
	 */
	@Test
	void testGetAllReliefPoint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByIdAndUser(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testGetReliefPointByIdAndUser() {
		fail("Not yet implemented");
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
		Optional<ReliefPoint> reliefPoint = Optional.empty();
//		Mockito.when(reliefPointRepository.findById(r_id).orElseThrow()).thenReturn(reliefPoint);
//		
//		Mockito.when(reliefPointRepository.save(reliefPoint)).thenReturn(reliefPoint);
//		 reliefPointService.updateStatusReliefPoint(r_id, status);
//		assertEquals(status, );
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#deleteReliefPointById(java.lang.Long)}.
	 */
	@Test
	void testDeleteReliefPointById() {
		fail("Not yet implemented");
	}

}
