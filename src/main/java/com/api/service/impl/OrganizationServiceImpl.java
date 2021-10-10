package com.api.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.OrganizationDto;
import com.api.entity.Organization;
import com.api.repositories.OrganizationRepository;
import com.api.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<Organization> getAllOrganzization() {
		// TODO Auto-generated method stub
		List<Organization> orgs = organizationRepository.findAll();
		List<OrganizationDto> orgDto = orgs.stream().map(org -> modelMapper.map(org, OrganizationDto.class)).collect(Collectors.toList()); 
		int x;
		return orgs;
	}

}
