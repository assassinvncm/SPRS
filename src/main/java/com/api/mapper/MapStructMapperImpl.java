package com.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.dto.AddressDto;
import com.api.dto.GroupDto;
import com.api.dto.ItemDto;
import com.api.dto.OrganizationDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.RequestDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Item;
import com.api.entity.Organization;
import com.api.entity.ReliefPoint;
import com.api.entity.Request;
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

		List<GroupDto> lstGroupDto = lstGroup.stream().map(group -> {
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
		userDto.setOrganization(organizationToOrganizationDto(user.getOrganization()));
		userDto.setPassword(user.getPassword());
		userDto.setIsActive(user.getIsActive());
		return userDto;
	}

	@Override
	public User userDtoToUser(UserDto userDto) {
		// TODO Auto-generated method stub
		if (userDto == null) {
			return null;
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setPhone(userDto.getPhone());
		user.setFull_name(userDto.getFull_name());
		user.setDob(userDto.getDob());
		user.setPassword(userDto.getPassword());
		user.setIsActive(userDto.getIsActive());
		user.setAddress(addressDtoToAddress(userDto.getAddress()));
		return user;
	}

	@Override
	public RequestDto requestToRequestDto(Request request) {
		// TODO Auto-generated method stub
		if (request == null) {
			return null;
		}

		RequestDto requestDto = new RequestDto();
		requestDto.setId(request.getId());
		requestDto.setStatus(request.getStatus());
		requestDto.setMessage(request.getMessage());
		requestDto.setTimestamp(request.getTimestamp());
		requestDto.setType(request.getType());
		requestDto.setUser(userToUserDto(request.getUser()));
		return requestDto;
	}

	@Override
	public List<RequestDto> lstRequestToRequestDto(List<Request> lstRequests) {
		// TODO Auto-generated method stub
		if (lstRequests == null) {
			return null;
		}
		List<RequestDto> lstRequestDto = lstRequests.stream().map(request -> {
			return requestToRequestDto(request);
		}).collect(Collectors.toList());

		return lstRequestDto;
	}

	@Override
	public ItemDto itemToItemDto(Item item) {
		// TODO Auto-generated method stub
		if (item == null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setName(item.getName());
		itemDto.setUnit(item.getUnit());
		itemDto.setDescription(item.getDescription());
		return itemDto;
	}

	@Override
	public Item itemDtoToItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		if (itemDto == null) {
			return null;
		}
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		item.setUnit(itemDto.getUnit());
		item.setDescription(itemDto.getDescription());
		return item;
	}

	@Override
	public List<ItemDto> lstitemToItemDto(List<Item> lstItem) {
		// TODO Auto-generated method stub
		if (lstItem == null) {
			return null;
		}

		List<ItemDto> lstItemDto = lstItem.stream().map(item -> {
			return itemToItemDto(item);
		}).collect(Collectors.toList());
		return lstItemDto;
	}

	@Override
	public List<Item> lstItemDtoToItem(List<ItemDto> lstItemDto) {
		// TODO Auto-generated method stub
		if (lstItemDto == null) {
			return null;
		}

		List<Item> lstItem = lstItemDto.stream().map(itemDto -> {
			return itemDtoToItem(itemDto);
		}).collect(Collectors.toList());
		return lstItem;
	}

	@Override
	public ReliefPointDto reliefPointToreliefPointDto(ReliefPoint reliefPoint) {
		// TODO Auto-generated method stub
		if (reliefPoint == null) {
			return null;
		}
		ReliefPointDto reliefPointDto = new ReliefPointDto();
		reliefPointDto.setId(reliefPoint.getId());
		reliefPointDto.setName(reliefPoint.getName());
		reliefPointDto.setClose_time(reliefPoint.getClose_time());
		reliefPointDto.setDescription(reliefPoint.getDescription());
		reliefPointDto.setAddress(addressToAddressDto(reliefPoint.getAddress()));
		reliefPointDto.setReliefInformations(null);
		reliefPointDto.setUser_rp(userToUserDto(reliefPoint.getUser_rp()));
		
		
		return reliefPointDto;
	}

	@Override
	public ReliefPoint reliefPointDtoToreliefPoint(ReliefPointDto reliefPointDto) {
		// TODO Auto-generated method stub
		
		if (reliefPointDto == null) {
			return null;
		}
		ReliefPoint reliefPoint = new ReliefPoint();
		reliefPoint.setId(reliefPointDto.getId());
		reliefPoint.setName(reliefPointDto.getName());
		reliefPoint.setClose_time(reliefPointDto.getClose_time());
		reliefPoint.setDescription(reliefPointDto.getDescription());
		reliefPoint.setAddress(addressDtoToAddress(reliefPointDto.getAddress()));
		reliefPoint.setReliefInformations(null);
		reliefPoint.setUser_rp(userDtoToUser(reliefPointDto.getUser_rp()));
		
		return reliefPoint;
	}

}
