package com.api.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.api.entity.Image;
import com.api.entity.StoreCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class StoreDto {
	private long id;
	
	@JsonProperty("name")
	@NotEmpty
	private String name;
	
	@JsonProperty("description")
	private String description;

	@NotEmpty
	private String open_time;

	@NotEmpty
	private String close_time;
	
	private String status;
	
	private UserDto user_st;
	
	@JsonProperty("store_category")
	private List<StoreCategory> storeDetail;
	
	@JsonProperty("address")
	@NotNull
	private AddressDto address;
	
	@JsonIgnore
    private List<Image> lstImage;

	public List<Image> getLstImage() {
		return lstImage;
	}

	public void setLstImage(List<Image> lstImage) {
		this.lstImage = lstImage;
	}

	public UserDto getUser_st() {
		return user_st;
	}

	public void setUser_st(UserDto user_st) {
		this.user_st = user_st;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}

	public String getClose_time() {
		return close_time;
	}

	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<StoreCategory> getStoreDetail() {
		return storeDetail;
	}

	public void setStoreDetail(List<StoreCategory> storeDetail) {
		this.storeDetail = storeDetail;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	
}
