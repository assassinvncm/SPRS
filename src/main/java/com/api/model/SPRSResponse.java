package com.api.model;

import java.io.Serializable;

public class SPRSResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3564179977498207994L;
	
	private String code;
	private String description;
	private String errors;
	
	public SPRSResponse() {
		super();
	}
	public SPRSResponse(String code, String description, String errors) {
		super();
		this.code = code;
		this.description = description;
		this.errors = errors;
	}
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public String getErrors() {
		return errors;
	}
	
	
}
