package com.api.dto;

import java.util.List;

public class PushNotificationRequest {
	
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
