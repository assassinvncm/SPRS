package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_Organization")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4715377594031347875L;
	
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "founded")
	//@JsonProperty("founded")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date founded;
	
	@Column(name = "description")
	@JsonProperty("description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName="id")
	@JsonProperty("address")
	private Address address;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonProperty("user")
	private List<User> user;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonProperty("request")
	private List<Request> request;
	
}
