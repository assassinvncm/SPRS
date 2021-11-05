package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Store")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208186724167391866L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@Column(name = "open_time")
	private Time open_time;
	
	@Column(name = "close_time")
	private Time close_time;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;
	
	@OneToMany(mappedBy = "storePoint", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<StoreDetail> storeDetail;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@JsonIgnore
    private Address location;

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

	public Time getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Time open_time) {
		this.open_time = open_time;
	}

	public Time getClose_time() {
		return close_time;
	}

	public void setClose_time(Time close_time) {
		this.close_time = close_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public List<StoreDetail> getStoreDetail() {
		return storeDetail;
	}

	public void setStoreDetail(List<StoreDetail> storeDetail) {
		this.storeDetail = storeDetail;
	}
	
	
}
