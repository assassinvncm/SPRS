package com.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.dto.AddressDto;
import com.api.dto.GroupDto;
import com.api.dto.OrganizationDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Organization;
import com.api.entity.User;

@Component
public class MapStructMapperImpl implements MapStructMapper {

	@Override
	public AddressDto addressToAddressDto(Address address) {
		// TODO Auto-generated method stub
		if (address == null) {
			return null;
		}
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1(address.getAddressLine());
		addressDto.setId(address.getId());
		addressDto.setGPS_lati(address.getGPS_Lati());
		addressDto.setGPS_long(address.getGPS_Long());
		
		return addressDto;
	}

	@Override
	public Address addressDtoToAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		
		if (addressDto == null) {
			return null;
		}
		
		Address address = new Address();
		address.setAddressLine(addressDto.getAddressLine1());
		address.setId(addressDto.getId());
		address.setGPS_Lati(addressDto.getGPS_lati());
		address.setGPS_Long(addressDto.getGPS_long());
		
		return address;
	}

	@Override
	public GroupDto groupToGroupDto(Group group) {
		// TODO Auto-generated method stub
		
		if (group == null) {
			return null;
		}
		
		GroupDto groupDto = new GroupDto();
		groupDto.setId(group.getId());
		groupDto.setName(group.getName());
		groupDto.setLevel(group.getLevel());
		
		return groupDto;
	}
	
	@Override
	public List<GroupDto> lstGroupToGroupDto(List<Group> lstGroup) {
		// TODO Auto-generated method stub
		
		if (lstGroup == null) {
			return null;
		}
		
		List<GroupDto> lstGroupDto = lstGroup.stream().map(group ->{
			return groupToGroupDto(group);
		}).collect(Collectors.toList());
		
		return lstGroupDto;
	}

	@Override
	public OrganizationDto organizationToOrganizationDto(Organization organization) {
		// TODO Auto-generated method stub
		if (organization == null) {
			return null;
		}
		
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setId(organization.getId());
		organizationDto.setName(organization.getName());
		organizationDto.setFounded(organization.getFounded());
		
		return organizationDto;
	}

	@Override
	public Group groupDtoToGroup(GroupDto group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organization organizationDtoToOrganization(OrganizationDto organization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto userToUserDto(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return null;
		}
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setPhone(user.getPhone());
		userDto.setUsername(user.getUsername());
		userDto.setFull_name(user.getFull_name());
		userDto.setDob(user.getDob());
		userDto.setCreate_time(null);
		userDto.setGroups_user(lstGroupToGroupDto(user.getGroups_user()));
		userDto.setAddress(addressToAddressDto(user.getAddress()));
		return userDto;
	}

	@Override
	public User userDtoToUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
