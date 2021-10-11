package com.api.service;

import java.util.List;

import com.api.entity.Organization;
import com.api.entity.Request;

public interface RequestService {
	
	List<Request> getRequestbyOrganization(Long id);
	
	List<Request> getRequestbySysAdmin(Long id);
	
	Request handleRequest(Request req);
}
