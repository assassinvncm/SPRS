package com.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

public class Notification extends BaseEntity{
	
	//@Column
	private Long source;
	
	//@ManyToMany
	private User receiver;
	
	//@Column
	private String typeSource;
	
	//@Column
	private String message;
	
	//@Column
	private String status;
	
	//@Column(columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
}
