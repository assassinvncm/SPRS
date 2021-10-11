package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.OrganizationDto;
import com.api.dto.SPRSResponse;
import com.api.entity.Organization;
import com.api.service.OrganizationService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/organization-manage")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@RequestMapping(value = "/origanization", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOrganization() {
		List<Organization> orgs = organizationService.getAllOrganzization();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", orgs, null));
	}
}
