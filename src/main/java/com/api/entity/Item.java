package com.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item extends BaseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4881146337443164167L;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "Unit")
	private String unit;
	
	@Column(name = "Description")
	private String description;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<ReliefInformation> reliefInformation;
	
	
}
