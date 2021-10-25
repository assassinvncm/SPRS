package com.api.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RequestDto {

	private String type;

	private String status;

	private String message;

	private Date timestamp;

	private UserDto user;

	private GroupDto group;

	private OrganizationDto organization;
}
