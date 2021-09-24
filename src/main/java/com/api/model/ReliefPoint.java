package com.api.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Relief_Point")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReliefPoint  extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4021755107320804060L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "open_time")
	private Date open_time;
	
	@Column(name = "close_time")
	private Date close_time;
	
	@Column(name = "status")
	private String status;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user_rp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}

	public Date getClose_time() {
		return close_time;
	}

	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public User getUsers() {
//		return user_rp;
//	}
//
//	public void setUsers(User users) {
//		this.user_rp = users;
//	}
	
	

}
