package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "Relief_Point_Items")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Relief_Point_Items {
	
	@Id
	private Long id;
	
	@Column(name = "Quantity")
	private int quantity;
	
}
