package com.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -70187117365177866L;

	@Column(name = "name")
	private String name;

	@Column(name = "level")
	private int level;
	
	@ManyToMany(mappedBy = "groups_user")
	private List<User> users_groups = new ArrayList<User>();
	
	@ManyToMany
	@JoinTable(name = "group_permission",
			joinColumns = @JoinColumn(name = "group_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	List<Permission> group_permission = new ArrayList<Permission>();
	
	public List<Permission> getPermissions() {
		return group_permission;
	}
	public void setPermissions(List<Permission> permissions) {
		this.group_permission = permissions;
	}
	public List<User> getUsers() {
		return users_groups;
	}
	public void setUsers(List<User> users) {
		this.users_groups = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
