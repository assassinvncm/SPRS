package com.api.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Acceptance")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Acceptance extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6445578957991370978L;

	@Column(name = "request_name")
	private String request_name;
	
	@Column(name = "request_time")
	private Date request_time;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "type")
	private String type;
	
	@ManyToOne 
    @JoinColumn(name = "user_id") 
    private User user;
	
	public Acceptance() {
		super();
	}

	public Acceptance(String request_name, Date request_time, boolean status, String type, User user) {
		super();
		this.request_name = request_name;
		this.request_time = request_time;
		this.status = status;
		this.type = type;
		this.user = user;
	}

	public String getRequest_name() {
		return request_name;
	}

	public void setRequest_name(String request_name) {
		this.request_name = request_name;
	}

	public Date getRequest_time() {
		return request_time;
	}

	public void setRequest_time(Date request_time) {
		this.request_time = request_time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
