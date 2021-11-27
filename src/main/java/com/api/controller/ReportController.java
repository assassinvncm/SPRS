package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.dto.SPRSResponse;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.service.ReportService;
import com.ultils.Constants;

@RestController
@RequestMapping("sprs/api/report-manage")
public class ReportController {

	public static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportServ;
	
	@Autowired 
	ProcedureMapper mapper;

	@RequestMapping(value = "/getReport", method = RequestMethod.POST)
	public ResponseEntity<?> getAll(@RequestBody ReportDto rpdto) {
		logger.info("Start get report");
		List<ReportResultDto> rs = reportServ.getReport(rpdto);
		logger.info("End get report");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report success", "", rs, null));
	}

	@RequestMapping(value = "/getReportOverview", method = RequestMethod.GET)
	public ResponseEntity<?> getReportOverview() {
		logger.info("Start get report Overview");
		List<ReportResultDto> rs = reportServ.getReport(new ReportDto());
		logger.info("End get report Overview");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Overview success", "", rs, null));
	}
	
}
