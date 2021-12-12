package com.api.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.common.utils.DateUtils;
import com.ultils.Constants;

@RestController
@RequestMapping("sprs/api/report-manage")
public class ReportController {

	public static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportServ;
	
	@Autowired 
	ProcedureMapper mapper;

	@RequestMapping(value = "/getReportMonth", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN','PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportMonth(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Month");
		Map<String, Object> rs = reportServ.getReportMonth(rpdto);
		logger.info("End get report Month");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Month success", "", rs, null));
	}

	@RequestMapping(value = "/getReportYear", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN','PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportYear(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Year");
		Map<String, Object> rs = reportServ.getReportYear(rpdto);
		logger.info("End get report Year");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Year success", "", rs, null));
	}

	@RequestMapping(value = "/getReportProvince", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN','PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportProvince(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Province");
		Map<String, Object> rs = reportServ.getReportProvince(rpdto);
		logger.info("End get report Province");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Province success", "", rs, null));
	}

	@RequestMapping(value = "/getReportOverview", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN','PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportOverview() {
		logger.info("Start get report Overview");
		List<ReportResultDto> rs = reportServ.getReportOverview(new ReportDto());
		logger.info("End get report Overview");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Overview success", "", rs, null));
	}
	
}
