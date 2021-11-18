package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Permission")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085626722387918888L;
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "group_permission")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Group> groups_link = new ArrayList<Group>();
	
	public Collection<Group> getGroups() {
		return groups_link;
	}

	public void setGroups(List<Group> groups) {
		this.groups_link = groups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
