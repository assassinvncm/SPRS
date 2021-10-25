package com.api.mapper;

import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.GroupDto;
import com.api.dto.OrganizationDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.User;

public interface MapStructMapper {
	
	UserDto userToUserDto(User user);
	
	AddressDto addressToAddressDto(Address address);
	
	GroupDto groupToGroupDto(Group group);
	
	List<GroupDto> lstGroupToGroupDto(List<Group> lstGroup);
	
	OrganizationDto organizationToOrganizationDto(Organization organization);
	
	User userDtoToUser(UserDto userDto);
	
	Address addressDtoToAddress(AddressDto addressDto);
	
	Group groupDtoToGroup(GroupDto group);
	
	Organization organizationDtoToOrganization(OrganizationDto organization);
	
}
