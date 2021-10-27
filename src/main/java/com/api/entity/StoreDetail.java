package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Store_Category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StoreDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1593227020069587312L;
	
	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName="id")
	private Store storePoint;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private String quantity;

	public Store getStorePoint() {
		return storePoint;
	}

	public void setStorePoint(Store storePoint) {
		this.storePoint = storePoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}
