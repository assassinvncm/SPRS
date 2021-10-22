package com.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.City;
import com.api.repositories.CityRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/utilities")
public class UtilitieController {
	
	@Autowired
	CityRepository cityRepo;
	
	@RequestMapping("/loadData")
	public ResponseEntity<?> loadData() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String path = "E:\\Data FPT University\\Project_FPT_20210528\\SPRS\\Source\\Source_api\\address.json";
		List<City> someClassObject = mapper.readValue(new File(path), new TypeReference<List<City>>(){});
		//Iterable<City> iterable  = someClassObject;
		//cityRepo.saveAll(iterable);
		someClassObject.stream().forEach(obj -> {
			cityRepo.save(obj);
		});
	    System.out.println(someClassObject);
		return ResponseEntity.ok(someClassObject);
	}

}
