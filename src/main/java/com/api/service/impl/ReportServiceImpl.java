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
		String group_by = "";
		switch (rpdto.getType_time()) {
			case 1: group_by = "m_year";
				break;
			case 2: group_by = "m_month";
				break;
			case 3: group_by = "m_day";
				break;
			default:
				group_by = "";
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, rpdto.getType_point()
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		int total = 0;
		for (ReportResultDto reportResultDto : lstRs) {
			total += reportResultDto.getTotal();
		}

		for (ReportResultDto reportResultDto : lstRs) {
			double percent = reportResultDto.getTotal()*100/total;
			reportResultDto.setTotal(percent);
		}
		
		return lstRs;
	}

}
