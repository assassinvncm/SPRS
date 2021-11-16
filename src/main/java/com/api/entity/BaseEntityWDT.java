package com.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntityWDT {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(updatable = false)
	public String create_by;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@Column
	public String modified_by;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Timestamp modified_date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Timestamp getModified_date() {
		return modified_date;
	}
	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}
	
	
	
}

