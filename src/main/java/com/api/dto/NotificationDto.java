package com.api.dto;

import java.sql.Timestamp;

public class NotificationDto {

	private Long source;

	private String typeSource;

	private String message;
	
	private String status;
	
	public Timestamp create_time;

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public String getTypeSource() {
		return typeSource;
	}

	public void setTypeSource(String typeSource) {
		this.typeSource = typeSource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}
