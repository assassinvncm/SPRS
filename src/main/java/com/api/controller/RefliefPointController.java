package com.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reliefPoint")
public class RefliefPointController {
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createReliefPoint(@RequestBody Object reliefPoint) {
		
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void getReliefPoints() {
		
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public void getReliefPointById(@PathVariable(value = "id") Long id ) {
		
	}
	
//	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//	public void getReliefPointByArea(@PathVariable(value = "id") String id ) {
//		
//	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateReliefPoint(@RequestBody Object reliefPoint) {
		
	}
	
	
	
}
