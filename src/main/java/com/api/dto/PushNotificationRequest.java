package com.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.Property;

public class PushNotificationRequest {
	
	@JsonProperty("lstToken")
	private List<String> lstToken;
	private String title;
	private String body;
	
	public List<String> getTarget() {
		return lstToken;
	}
	public void setTarget(List<String> lstToken) {
		this.lstToken = lstToken;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
