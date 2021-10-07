package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "SPRS_Item")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
	
	@Id
	private Long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Unit")
	private String unit;
	
	@Column(name = "Description")
	private String description;
	
}
