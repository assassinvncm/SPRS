package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.api.dto.*;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {

	private RequestServiceImpl requestServiceImplUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		requestServiceImplUnderTest = new RequestServiceImpl();
		requestServiceImplUnderTest.requestRepository = mock(RequestRepository.class);
		requestServiceImplUnderTest.organizationRepository = mock(OrganizationRepository.class);
		requestServiceImplUnderTest.mapStructMapper = mock(MapStructMapper.class);
	}

	@Test
	void testGetRequestbyOrganization_UTCID01() {
		// Setup
		// Configure RequestRepository.findByOrganization_id(...).
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Organization org1 = new Organization();
		org1.setId(2L);
		org1.setName("To chuc tu thien");
		req1.setOrganization(org1);

		requests.add(req1);
		when(requestServiceImplUnderTest.requestRepository.findByOrganization_id(org1.getId())).thenReturn(requests);

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbyOrganization(org1.getId());

		// Verify the results
		assertEquals(requests.get(0).getId(), result.get(0).getId());
	}

	@Test
	void testGetRequestbyOrganization_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findByOrganization_id(0L))
				.thenReturn(Collections.emptyList());

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbyOrganization(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testGetRequestbySysAdmin_UTCID01() {
		// Setup
		// Configure RequestRepository.findByGroup_id(...).
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);

		requests.add(req1);
		when(requestServiceImplUnderTest.requestRepository.findByGroup_id(requests.get(0).getGroup().getId()))
				.thenReturn(requests);

		// Run the test
		final List<Request> result = requestServiceImplUnderTest
				.getRequestbySysAdmin(requests.get(0).getGroup().getId());

		// Verify the results
		assertEquals(requests.get(0).getGroup().getId(), result.get(0).getGroup().getId());
		assertEquals(requests.get(0).getGroup().getName(), result.get(0).getGroup().getName());
	}

	@Test
	void testGetRequestbySysAdmin_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findByGroup_id(0L)).thenReturn(Collections.emptyList());

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbySysAdmin(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestSysAdmin_UTCID01() {
		// Setup
		// Configure RequestRepository.filterRequestOfAdmin(...).

		List<RequestDto> requestDtos = new ArrayList<RequestDto>();
		RequestDto reqDto1 = new RequestDto();
		reqDto1.setId(1L);
		requestDtos.add(reqDto1);
		requestDtos.add(reqDto1);
		
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);;
		
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfAdmin(0L, "status", "search")).thenReturn(requests);
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(requests)).thenReturn(requestDtos);

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestSysAdmin(0L, "status", "search");

		// Verify the results
		assertEquals(requestDtos.get(0).getId(), result.get(0).getId());
	}

	@Test
	void testFilterRequestSysAdmin_RequestRepositoryReturnsNoItems() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfAdmin(0L, "status", "search"))
				.thenReturn(Collections.emptyList());

		// Configure MapStructMapper.lstRequestToRequestDto(...).
		final PermissionDto permissionDto = new PermissionDto();
		permissionDto.setCode("code");
		permissionDto.setId(0L);
		permissionDto.setTo("to");
		permissionDto.setIcon("icon");
		permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto.setName("name");
		final PermissionDto permissionDto1 = new PermissionDto();
		permissionDto1.setCode("code");
		permissionDto1.setId(0L);
		permissionDto1.setTo("to");
		permissionDto1.setIcon("icon");
		permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto1.setName("name");
		final List<RequestDto> requestDtos = Arrays.asList(new RequestDto(0L, "type", "status", "message",
				new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
				new UserDto(0L, "username", "phone", "password", "full_name", "dob",
						Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(
										0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null,
														Arrays.asList(new Address("city", "province", "district", null,
																"addressLine", "gPS_Long", "gPS_Lati"))))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name",
										new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList(new SubDistrict("code", "name", null,
																Arrays.asList()))),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(),
								Arrays.asList())),
						Arrays.asList()),
				new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(new UserDto(0L, "username",
						"phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", null, Arrays.asList()), Arrays.asList()))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"),
										new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(), Arrays.asList())), Arrays.asList()),
				new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(
						new CityDto(0L, "code", "name"),
						new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
								Arrays.asList(new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						new SubDistrictDto(0L, "code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList(new SubDistrict("code", "name", null,
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								Arrays.asList()),
						"addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())));
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(Arrays.asList(new Request("type",
				"status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group())),
				new Group(),
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))),
						Arrays.asList()))))).thenReturn(requestDtos);

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestSysAdmin(0L, "status", "search");

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestSysAdmin_MapStructMapperReturnsNoItems() {
		// Setup
		// Configure RequestRepository.filterRequestOfAdmin(...).
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final List<Request> requests = Arrays.asList(new Request("type", "status", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList())));
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfAdmin(0L, "status", "search"))
				.thenReturn(requests);

		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(Arrays.asList(new Request("type",
				"status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group())),
				new Group(),
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))),
						Arrays.asList()))))).thenReturn(Collections.emptyList());

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestSysAdmin(0L, "status", "search");

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestOrgAdmin() {
		// Setup
		// Configure RequestRepository.filterRequestOfOrgAdmin(...).
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final List<Request> requests = Arrays.asList(new Request("type", "status", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList())));
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfOrgAdmin(0L, "status")).thenReturn(requests);

		// Configure MapStructMapper.lstRequestToRequestDto(...).
		final PermissionDto permissionDto = new PermissionDto();
		permissionDto.setCode("code");
		permissionDto.setId(0L);
		permissionDto.setTo("to");
		permissionDto.setIcon("icon");
		permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto.setName("name");
		final PermissionDto permissionDto1 = new PermissionDto();
		permissionDto1.setCode("code");
		permissionDto1.setId(0L);
		permissionDto1.setTo("to");
		permissionDto1.setIcon("icon");
		permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto1.setName("name");
		final List<RequestDto> requestDtos = Arrays.asList(new RequestDto(0L, "type", "status", "message",
				new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
				new UserDto(0L, "username", "phone", "password", "full_name", "dob",
						Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(
										0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null,
														Arrays.asList(new Address("city", "province", "district", null,
																"addressLine", "gPS_Long", "gPS_Lati"))))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name",
										new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList(new SubDistrict("code", "name", null,
																Arrays.asList()))),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(),
								Arrays.asList())),
						Arrays.asList()),
				new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(new UserDto(0L, "username",
						"phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", null, Arrays.asList()), Arrays.asList()))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"),
										new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(), Arrays.asList())), Arrays.asList()),
				new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(
						new CityDto(0L, "code", "name"),
						new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
								Arrays.asList(new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						new SubDistrictDto(0L, "code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList(new SubDistrict("code", "name", null,
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								Arrays.asList()),
						"addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())));
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(Arrays.asList(new Request("type",
				"status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group())),
				new Group(),
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))),
						Arrays.asList()))))).thenReturn(requestDtos);

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestOrgAdmin(0L, "status");

		// Verify the results
	}

	@Test
	void testFilterRequestOrgAdmin_RequestRepositoryReturnsNoItems() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfOrgAdmin(0L, "status"))
				.thenReturn(Collections.emptyList());

		// Configure MapStructMapper.lstRequestToRequestDto(...).
		final PermissionDto permissionDto = new PermissionDto();
		permissionDto.setCode("code");
		permissionDto.setId(0L);
		permissionDto.setTo("to");
		permissionDto.setIcon("icon");
		permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto.setName("name");
		final PermissionDto permissionDto1 = new PermissionDto();
		permissionDto1.setCode("code");
		permissionDto1.setId(0L);
		permissionDto1.setTo("to");
		permissionDto1.setIcon("icon");
		permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
		permissionDto1.setName("name");
		final List<RequestDto> requestDtos = Arrays.asList(new RequestDto(0L, "type", "status", "message",
				new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(),
				new UserDto(0L, "username", "phone", "password", "full_name", "dob",
						Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(
										0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null,
														Arrays.asList(new Address("city", "province", "district", null,
																"addressLine", "gPS_Long", "gPS_Lati"))))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name",
										new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList(new SubDistrict("code", "name", null,
																Arrays.asList()))),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(),
								Arrays.asList())),
						Arrays.asList()),
				new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(new UserDto(0L, "username",
						"phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false,
						new AddressDto(new CityDto(0L, "code", "name"),
								new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
										Arrays.asList(new SubDistrict("code", "name",
												new District("code", "name", null, Arrays.asList()), Arrays.asList()))),
								new SubDistrictDto(0L, "code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										Arrays.asList()),
								"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
						new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
								new AddressDto(new CityDto(0L, "code", "name"),
										new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
												Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))),
										new SubDistrictDto(0L, "code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine1", "addressLine2", "gPS_long", "gPS_lati"),
								Arrays.asList()),
						Arrays.asList(), Arrays.asList())), Arrays.asList()),
				new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(
						new CityDto(0L, "code", "name"),
						new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"),
								Arrays.asList(new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						new SubDistrictDto(0L, "code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList(new SubDistrict("code", "name", null,
												Arrays.asList(new Address("city", "province", "district", null,
														"addressLine", "gPS_Long", "gPS_Lati"))))),
								Arrays.asList()),
						"addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())));
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(Arrays.asList(new Request("type",
				"status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group())),
				new Group(),
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))),
						Arrays.asList()))))).thenReturn(requestDtos);

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestOrgAdmin(0L, "status");

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestOrgAdmin_MapStructMapperReturnsNoItems() {
		// Setup
		// Configure RequestRepository.filterRequestOfOrgAdmin(...).
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final List<Request> requests = Arrays.asList(new Request("type", "status", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList())));
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfOrgAdmin(0L, "status")).thenReturn(requests);

		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(Arrays.asList(new Request("type",
				"status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group())),
				new Group(),
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district", new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))),
						Arrays.asList()))))).thenReturn(Collections.emptyList());

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestOrgAdmin(0L, "status");

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testHandleRequest() {
		// Setup
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Request request = new Request("type", "status", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList()));

		// Run the test
		final Request result = requestServiceImplUnderTest.handleRequest(request);

		// Verify the results
	}

	@Test
	void testAcceptRequest() {
		// Setup
		// Run the test
		requestServiceImplUnderTest.acceptRequest(Arrays.asList(0L), 0L);

		// Verify the results
	}

	@Test
	void testRejectRequest() {
		// Setup
		// Run the test
		requestServiceImplUnderTest.RejectRequest(Arrays.asList(0L), 0L);

		// Verify the results
	}

}
