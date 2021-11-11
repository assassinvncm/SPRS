package com.api.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;


@MappedSuperclass
public class BaseEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(updatable = false)
	public String create_by;
	
	@Column(updatable = false)
	public Date create_time;
	
	@Column
	public String modified_by;
	
	@Column
	public Date modified_date;
	
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	
	
	
}
