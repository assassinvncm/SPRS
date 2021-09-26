//package com.api.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "Device")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//public class Device  extends BaseEntity implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 2563304982006721556L;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "token")
//	private String token;
//	
//	@Column(name = "location")
//	private String location;
//
////	@ManyToOne
////	@JoinColumn(name = "user_id",referencedColumnName="id")
////	private User user_dv;
////
////	public User getUser_dv() {
////		return user_dv;
////	}
////
////	public void setUser_dv(User user_dv) {
////		this.user_dv = user_dv;
////	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//}
