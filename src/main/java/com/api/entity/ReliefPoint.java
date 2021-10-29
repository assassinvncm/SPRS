package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
@Table(name = "SPRS_Relief_Point")
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

	@Column(name = "open_time")
	private Date open_time;
	
	@Column(name = "close_time")
	private Date close_time;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user_rp;
	
	@OneToMany(mappedBy = "reliefPoint", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<ReliefInformation> reliefInformations;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	@JsonIgnore
    private Address address;

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
	
	public User getUsers() {
		return user_rp;
	}

	public void setUsers(User users) {
		this.user_rp = users;
	}

	public User getUser_rp() {
		return user_rp;
	}

	public void setUser_rp(User user_rp) {
		this.user_rp = user_rp;
	}

	public List<ReliefInformation> getReliefInformations() {
		return reliefInformations;
	}

	public void setReliefInformations(List<ReliefInformation> reliefInformations) {
		this.reliefInformations = reliefInformations;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
