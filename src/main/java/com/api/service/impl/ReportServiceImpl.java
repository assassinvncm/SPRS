package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.ReportRepository;
import com.api.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	ReportRepository rpRepo;
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Override
	public List<ReportResultDto> getReport(ReportDto rpdto) {
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict()
				, rpdto.getSub_district()
				, rpdto.getCity()
				, rpdto.getMonth()
				, rpdto.getYear()
				, rpdto.getType_point());
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		return lstRs;
	}

}
