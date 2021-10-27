package com.api.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.entity.Item;
import com.api.entity.User;

public class ReliefInformationDto {
	
	private long id;
	
	private int quantity;
	
	private ReliefPointDto reliefPoint;
	
	private Item item;
	
}
