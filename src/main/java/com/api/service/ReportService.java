package com.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;

public interface ReportService {
	List<ReportResultDto> getReportYear(ReportDto rpdto);
	List<ReportResultDto> getReportMonth(ReportDto rpdto);
	List<ReportResultDto> getReportOverview(ReportDto rpdto);
}
