package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.ReportRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceImplTest {

    private ReportServiceImpl reportServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        reportServiceImplUnderTest = new ReportServiceImpl();
        reportServiceImplUnderTest.rpRepo = mock(ReportRepository.class);
        reportServiceImplUnderTest.mapper = mock(ProcedureMapper.class);
    }

    @Test
    void testGetReportYear() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYear(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportYear_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYear(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportYear_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYear(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonth() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonth(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonth_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonth(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonth_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonth(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportYearORG() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYearORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportYearORG_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYearORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportYearORG_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportYearORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonthORG() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonthORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonthORG_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonthORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportMonthORG_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportMonthORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportOverview() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportOverview();

        // Verify the results
    }

    @Test
    void testGetReportOverview_ReportRepositoryReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportOverview();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetReportOverview_ProcedureMapperReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportOverview();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetReportUserORGOverview() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportUserORGOverview();

        // Verify the results
    }

    @Test
    void testGetReportUserORGOverview_ReportRepositoryReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMapping(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportUserORGOverview();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetReportUserORGOverview_ProcedureMapperReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReport(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMapping(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final List<ReportResultDto> result = reportServiceImplUnderTest.getReportUserORGOverview();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetReportProvince() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCity(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMappingProvince(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvince(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportProvince_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCity(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMappingProvince(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvince(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportProvince_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCity(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by")).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvince(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportProvinceORG() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCityORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMappingProvince(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvinceORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportProvinceORG_ReportRepositoryReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCityORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMappingProvince(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvinceORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportProvinceORG_ProcedureMapperReturnsNoItems() {
        // Setup
        final ReportDto rpdto = new ReportDto();
        rpdto.setOrg_id(0L);
        rpdto.setDate_from("date_from");
        rpdto.setDate_to("date_to");
        rpdto.setType_time(0);
        rpdto.setType_chart(0);
        rpdto.setDistrict_id(0);
        rpdto.setSub_district_id(0);
        rpdto.setCity_id(0);
        rpdto.setType_point(new int[]{0});

        when(reportServiceImplUnderTest.rpRepo.getReportCityORG(0, 0, 0, "p_date_from", "p_date_to", "p_type_point", "p_group_by", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMappingProvince(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportProvinceORG(rpdto);

        // Verify the results
    }

    @Test
    void testGetReportTopUserORG() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReportTopUserORG("p_date_from", "p_date_to", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));

        // Configure ProcedureMapper.reportMappingTopUser(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingTopUser(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportTopUserORG(0L);

        // Verify the results
    }

    @Test
    void testGetReportTopUserORG_ReportRepositoryReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReportTopUserORG("p_date_from", "p_date_to", 0L)).thenReturn(Collections.emptyList());

        // Configure ProcedureMapper.reportMappingTopUser(...).
        final ReportResultDto reportResultDto = new ReportResultDto();
        reportResultDto.setName("name");
        reportResultDto.setDay(1);
        reportResultDto.setMonth(1);
        reportResultDto.setYear(2020);
        reportResultDto.setTotal(0.0);
        reportResultDto.setType_point(0);
        final List<ReportResultDto> reportResultDtos = Arrays.asList(reportResultDto);
        when(reportServiceImplUnderTest.mapper.reportMappingTopUser(Arrays.asList(new Object[]{"value"}))).thenReturn(reportResultDtos);

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportTopUserORG(0L);

        // Verify the results
    }

    @Test
    void testGetReportTopUserORG_ProcedureMapperReturnsNoItems() {
        // Setup
        when(reportServiceImplUnderTest.rpRepo.getReportTopUserORG("p_date_from", "p_date_to", 0L)).thenReturn(Arrays.asList(new Object[]{"value"}));
        when(reportServiceImplUnderTest.mapper.reportMappingTopUser(Arrays.asList(new Object[]{"value"}))).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reportServiceImplUnderTest.getReportTopUserORG(0L);

        // Verify the results
    }

}
