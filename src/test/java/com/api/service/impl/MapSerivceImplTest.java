package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchMapResponse;
import com.api.repositories.MapRepository;

@SpringBootTest
@AutoConfigureMockMvc
class MapSerivceImplTest {

	@MockBean
	MapRepository mapRepository;
	
	@MockBean
	MapSerivceImpl mapSerivceImpl;
	
	@Test
	void testFindAllPoints_UTCID01() {
		//init input data
		double la = 9.81791814590403;
		double lo = 105.5900620709036;
		double radius = 100.3;
		String typePoint = null;
		
		//mock data
		List<Object[]> mapPoints = new ArrayList<Object[]>();
		Object[] mappost = new Object[] {1,"abc","12.3412123","5.2132132","acs"};
		
		//mock
		Mockito.when(mapRepository.getPoints(la,lo,radius,typePoint)).thenReturn(mapPoints);
		
		List<MapPointsDto> lstMapPoints = new ArrayList<MapPointsDto>();
		//call api
		lstMapPoints = mapSerivceImpl.findAllPoints(la, lo, radius, typePoint);
		
		//compare result
		assertEquals(mappost[0], lstMapPoints.get(0).getId());
		
	}

}
