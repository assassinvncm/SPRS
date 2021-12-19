package com.api.mapper.proc_mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.dto.ReportResultDto;
import com.api.entity.Device;
import com.api.entity.Store;

@ExtendWith(MockitoExtension.class)
class ProcedureMapperTest {

	private ProcedureMapper procedureMapperUnderTest;

	@BeforeEach
	void setUp() {
		procedureMapperUnderTest = new ProcedureMapper();
	}

	@Test
	void testGetStoreByStatusOrType_Mapper() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "value" };
		lstObject.add(obj);
		// Run the test
		final List<Store> result = procedureMapperUnderTest
				.getStoreByStatusOrType_Mapper(lstObject);

		// Verify the results
	}

	@Test
	void testGetDevice() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "value" };
		lstObject.add(obj);
		// Run the test
		final List<Device> result = procedureMapperUnderTest.getDevice(lstObject);

		// Verify the results
	}

	@Test
	void testReportMapping() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "value" };
		lstObject.add(obj);
		// List<Object[]> lstObject = new A

		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest.reportMapping(lstObject);

		// Verify the results
	}

	@Test
	void testReportMappingProvince() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "value" };
		lstObject.add(obj);
		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest
				.reportMappingProvince(lstObject);

		// Verify the results
	}

	@Test
	void testReportMappingTopUser() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "12", "duongpt" };
		lstObject.add(obj);
		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest
				.reportMappingTopUser(lstObject);

		// Verify the results
	}

}
