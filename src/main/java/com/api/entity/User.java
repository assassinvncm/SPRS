package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1845793972265729408L;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String full_name;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "create_time")
	private Date create_time;
	
	@Column(name = "isActive")
	private Boolean isActive;

	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinTable(name = "SPRS_user_group",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name ="group_id"))
	private List<Group> groups_user = new ArrayList<Group>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<Acceptance> acceptances;
	
//	@OneToMany(mappedBy = "user_rp")
//	private List<ReliefPoint> reliefPoints = new ArrayList<ReliefPoint>();
//
//	@OneToMany(mappedBy = "users_dv")
//	private List<Device> devices = new ArrayList<Device>();
//	
//	public List<ReliefPoint> getReliefPoints() {
//		return reliefPoints;
//	}
//
//	public void setReliefPoints(List<ReliefPoint> reliefPoints) {
//		this.reliefPoints = reliefPoints;
//	}
//
//	public List<Device> getDevices() {
//		return devices;
//	}
//
//	public void setDevices(List<Device> devices) {
//		this.devices = devices;
//	}

	public List<Group> getGroups_user() {
		return groups_user;
	}

	public User(String username, String phone, String password, String full_name, String dob, String address,
		Date create_time, Boolean isActive, List<Group> groups_user, List<Acceptance> acceptances) {
	super();
	this.username = username;
	this.phone = phone;
	this.password = password;
	this.full_name = full_name;
	this.dob = dob;
	this.address = address;
	this.create_time = create_time;
	this.isActive = isActive;
	this.groups_user = groups_user;
	this.acceptances = acceptances;
}

	public User() {
		super();
	}

	public List<Acceptance> getAcceptances() {
		return acceptances;
	}

	public void setAcceptances(List<Acceptance> acceptances) {
		this.acceptances = acceptances;
	}

	public void setGroups_user(List<Group> groups_user) {
		this.groups_user = groups_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}