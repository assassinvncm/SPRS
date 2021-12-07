package com.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.ReportRepository;
import com.api.service.ReportService;
import com.common.utils.DateUtils;
import com.ultils.Ultilities;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	ReportRepository rpRepo;
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Override
	public List<ReportResultDto> getReportYear(ReportDto rpdto) {
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String monthAgo = DateUtils.getMonthAgo("yyyy-MM-dd", 12);
		String group_by = "m_month";
		String type_point = "";
		rpdto.setDate_from(monthAgo);
		rpdto.setDate_to(currDate);
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
//		final List<Integer> labelsTemp = new ArrayList<Integer>();
//		List<Integer> labels = new ArrayList<Integer>();
//		List<Integer> values1 = new ArrayList<Integer>();
//		List<Integer> values2 = new ArrayList<Integer>();
//		List<Integer> values3 = new ArrayList<Integer>();
//		List<Integer> values4 = new ArrayList<Integer>();
//		lstRs.forEach(l -> labelsTemp.add((int)l.getMonth()));
//		labels = Ultilities.getLabelReport(labelsTemp);
//
//		for (Integer label : labels) {
//			for (ReportResultDto reportResultDto : lstRs) {
//				if(label == reportResultDto.getMonth() && reportResultDto.getType_point() == 1) {
//					values1.add((int)reportResultDto.getTotal());
//				}else {
//					values1.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 2) {
//					values2.add((int)reportResultDto.getTotal());
//				}else {
//					values2.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 3) {
//					values3.add((int)reportResultDto.getTotal());
//				}else {
//					values3.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 4) {
//					values4.add((int)reportResultDto.getTotal());
//				}else {
//					values4.add(0);
//				}
//			}
//		}
//		Map<String, Object> data = new HashMap<>();
//		data.put("ReliefPoint", values1);
//		data.put("StorePoint", values2);
//		data.put("Organization", values3);
//		data.put("SOS", values4);
//		Map<String, Object> response = new HashMap<>();
//        response.put("data", data);
//        response.put("label", labels);
		return lstRs;
	}
	

	@Override
	public List<ReportResultDto> getReportMonth(ReportDto rpdto) {
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
		String type_point = "";
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
//		final List<Integer> labelsTemp = new ArrayList<Integer>();
//		List<Integer> labels = new ArrayList<Integer>();
//		List<Integer> values1 = new ArrayList<Integer>();
//		List<Integer> values2 = new ArrayList<Integer>();
//		List<Integer> values3 = new ArrayList<Integer>();
//		List<Integer> values4 = new ArrayList<Integer>();
//		if(group_by.equals("m_year")) {
//			lstRs.forEach(l -> labelsTemp.add((int)l.getYear()));
//		}else {
//			lstRs.forEach(l -> labelsTemp.add((int)l.getMonth()));
//		}
//		labels = Ultilities.getLabelReport(labelsTemp);
//		for (ReportResultDto reportResultDto : lstRs) {
//			for (Integer label : labels) {
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 1) {
//					values1.add((int)reportResultDto.getTotal());
//				}else {
//					values1.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 2) {
//					values2.add((int)reportResultDto.getTotal());
//				}else {
//					values2.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 3) {
//					values3.add((int)reportResultDto.getTotal());
//				}else {
//					values3.add(0);
//				}
//				if(Ultilities.checkExistIn((int)label, rpdto.getType_point()) && label == 4) {
//					values4.add((int)reportResultDto.getTotal());
//				}else {
//					values4.add(0);
//				}
//			}
//		}
//		Map<String, Object> data = new HashMap<>();
//		data.put("ReliefPoint", values1);
//		data.put("StorePoint", values2);
//		data.put("Organization", values3);
//		data.put("SOS", values4);
//		Map<String, Object> response = new HashMap<>();
//        response.put("data", data);
//        response.put("label", labels);
		return lstRs;
	}

	@Override
	public List<ReportResultDto> getReportOverview(ReportDto rpdto) {
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
		String type_point = "";
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
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
