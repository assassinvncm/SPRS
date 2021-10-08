package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ReliefInformation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReliefInformation extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991088729929895455L;

	@Column(name = "Quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "reliefPoint_id", referencedColumnName="id")
	private User reliefPoint;
	
	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName="id")
	private Item item;
	
}
