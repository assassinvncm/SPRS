package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public List<Request> getRequestbyOrganization(Long id) {
		// TODO Auto-generated method stub
		
		return requestRepository.findByOrganization_id(id);
	}

}
