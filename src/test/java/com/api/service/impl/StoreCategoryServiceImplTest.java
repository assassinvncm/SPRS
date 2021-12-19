package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.api.dto.StoreCategoryDto;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.StoreCategoryRepository;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StoreCategoryServiceImplTest {

    private StoreCategoryServiceImpl storeCategoryServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        storeCategoryServiceImplUnderTest = new StoreCategoryServiceImpl();
        storeCategoryServiceImplUnderTest.cateRepository = mock(StoreCategoryRepository.class);
        storeCategoryServiceImplUnderTest.mapStructMapper = mock(MapStructMapper.class);
    }

    @Test
    void testGetAllCategory() {
        // Setup
        // Configure MapStructMapper.lstStoreCateToStoreCateDto(...).
        final StoreCategoryDto storeCategoryDto = new StoreCategoryDto();
        storeCategoryDto.setName("name");
        final List<StoreCategoryDto> storeCategoryDtos = Arrays.asList(storeCategoryDto);
        when(storeCategoryServiceImplUnderTest.mapStructMapper.lstStoreCateToStoreCateDto(Arrays.asList(new StoreCategory()))).thenReturn(storeCategoryDtos);

        // Run the test
        final List<StoreCategoryDto> result = storeCategoryServiceImplUnderTest.getAllCategory();

        // Verify the results
    }

    @Test
    void testGetAllCategory_MapStructMapperReturnsNoItems() {
        // Setup
        when(storeCategoryServiceImplUnderTest.mapStructMapper.lstStoreCateToStoreCateDto(Arrays.asList(new StoreCategory()))).thenReturn(Collections.emptyList());

        // Run the test
        final List<StoreCategoryDto> result = storeCategoryServiceImplUnderTest.getAllCategory();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testInsertCategory() {
        // Setup
        final StoreCategoryDto storeCategory = new StoreCategoryDto();
        storeCategory.setName("name");

        // Configure StoreCategoryRepository.findByName(...).
        final StoreCategory storeCategory2 = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory2.setCategory_store(Arrays.asList(store));
        storeCategory2.setName("name");
        final Optional<StoreCategory> storeCategory1 = Optional.of(storeCategory2);
        when(storeCategoryServiceImplUnderTest.cateRepository.findByName("name")).thenReturn(storeCategory1);

        // Configure MapStructMapper.cateDtoToCate(...).
        final StoreCategory storeCategory3 = new StoreCategory();
        final Store store1 = new Store();
        store1.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store1.setImages(new Image("img_url"));
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
        group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store1.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))));
        store1.setStore_category(Arrays.asList(new StoreCategory()));
        store1.setName("name");
        store1.setDescription("description");
        store1.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store1.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        final Group group3 = new Group();
        group3.setPlatform(0);
        group3.setName("name");
        group3.setLevel(0);
        group3.setCode("code");
        final Permission permission3 = new Permission();
        permission3.setLevel(0);
        permission3.setNode_index(0);
        permission3.setNode_from(0);
        permission3.setNode_to(0);
        permission3.setTo_page("to_page");
        permission3.setIcon_name("icon_name");
        permission3.setCode("code");
        permission3.setName("name");
        group3.setGroup_permission(Arrays.asList(permission3));
        group3.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store1.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group3)));
        store1.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory3.setCategory_store(Arrays.asList(store1));
        storeCategory3.setName("name");
        when(storeCategoryServiceImplUnderTest.mapStructMapper.cateDtoToCate(any(StoreCategoryDto.class))).thenReturn(storeCategory3);

        // Run the test
        storeCategoryServiceImplUnderTest.insertCategory(storeCategory);

        // Verify the results
    }

    @Test
    void testInsertCategory_StoreCategoryRepositoryReturnsAbsent() {
        // Setup
        final StoreCategoryDto storeCategory = new StoreCategoryDto();
        storeCategory.setName("name");

        when(storeCategoryServiceImplUnderTest.cateRepository.findByName("name")).thenReturn(Optional.empty());

        // Configure MapStructMapper.cateDtoToCate(...).
        final StoreCategory storeCategory1 = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory1.setCategory_store(Arrays.asList(store));
        storeCategory1.setName("name");
        when(storeCategoryServiceImplUnderTest.mapStructMapper.cateDtoToCate(any(StoreCategoryDto.class))).thenReturn(storeCategory1);

        // Run the test
        storeCategoryServiceImplUnderTest.insertCategory(storeCategory);

        // Verify the results
    }

    @Test
    void testDeleteCategory() {
        // Setup
        final StoreCategoryDto storeCategoryDto = new StoreCategoryDto();
        storeCategoryDto.setName("name");
        final List<StoreCategoryDto> storeCategory = Arrays.asList(storeCategoryDto);

        // Configure MapStructMapper.lstStoreCateDtoToStoreCate(...).
        final StoreCategory storeCategory1 = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory1.setCategory_store(Arrays.asList(store));
        storeCategory1.setName("name");
        final List<StoreCategory> storeCategories = Arrays.asList(storeCategory1);
        when(storeCategoryServiceImplUnderTest.mapStructMapper.lstStoreCateDtoToStoreCate(Arrays.asList(new StoreCategoryDto()))).thenReturn(storeCategories);

        // Run the test
        storeCategoryServiceImplUnderTest.deleteCategory(storeCategory);

        // Verify the results
    }

    @Test
    void testDeleteCategory_MapStructMapperReturnsNoItems() {
        // Setup
        final StoreCategoryDto storeCategoryDto = new StoreCategoryDto();
        storeCategoryDto.setName("name");
        final List<StoreCategoryDto> storeCategory = Arrays.asList(storeCategoryDto);
        when(storeCategoryServiceImplUnderTest.mapStructMapper.lstStoreCateDtoToStoreCate(Arrays.asList(new StoreCategoryDto()))).thenReturn(Collections.emptyList());

        // Run the test
        storeCategoryServiceImplUnderTest.deleteCategory(storeCategory);

        // Verify the results
    }

    @Test
    void testUpdateCategory() {
        // Setup
        final StoreCategoryDto storeCategory = new StoreCategoryDto();
        storeCategory.setName("name");

        // Configure StoreCategoryRepository.findByName(...).
        final StoreCategory storeCategory2 = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory2.setCategory_store(Arrays.asList(store));
        storeCategory2.setName("name");
        final Optional<StoreCategory> storeCategory1 = Optional.of(storeCategory2);
        when(storeCategoryServiceImplUnderTest.cateRepository.findByName("name")).thenReturn(storeCategory1);

        // Run the test
        storeCategoryServiceImplUnderTest.updateCategory(storeCategory);

        // Verify the results
    }

    @Test
    void testUpdateCategory_StoreCategoryRepositoryReturnsAbsent() {
        // Setup
        final StoreCategoryDto storeCategory = new StoreCategoryDto();
        storeCategory.setName("name");

        when(storeCategoryServiceImplUnderTest.cateRepository.findByName("name")).thenReturn(Optional.empty());

        // Run the test
        storeCategoryServiceImplUnderTest.updateCategory(storeCategory);

        // Verify the results
    }

}
