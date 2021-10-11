package com.api.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.service.RequestService;
import com.exception.AppException;

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

	@Override
	public List<Request> getRequestbySysAdmin(Long id) {
		// TODO Auto-generated method stub
		
		return requestRepository.findByGroup_id(id);
	}

	@Override
	public Request handleRequest(Request request) {
		// TODO Auto-generated method stub
		Request req = requestRepository.findById(request.getId()).orElseThrow(()-> new AppException(405,"Request not exist!"));
		req.setStatus(request.getStatus());
		if(req.getStatus().equals("accept")) {
			req.getUser().setIsActive(true);
		}
		if(req.getStatus().equals("reject")) {
			req.getUser().setIsActive(false);
		}
		
		//BeanUtils.copyProperties(request, req);
		return requestRepository.save(req);
	}

}
