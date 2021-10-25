package com.api.dto;

import java.util.Collection;

import com.api.entity.Group;

public class PermissionDto {
	
	private long id;

	private String code;

	private String name;

	private String description;

	private Collection<GroupDto> groups_link;
	
	

	public PermissionDto() {
		super();
	}

	public PermissionDto(long id, String code, String name, String description, Collection<GroupDto> groups_link) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.groups_link = groups_link;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<GroupDto> getGroups_link() {
		return groups_link;
	}

	public void setGroups_link(Collection<GroupDto> groups_link) {
		this.groups_link = groups_link;
	}
	
	
}
