package com.api.mapper;

import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.dto.GroupDto;
import com.api.dto.ItemDto;
import com.api.dto.OrganizationDto;
import com.api.dto.ReliefInformationDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.RequestDto;
import com.api.dto.SOSDto;
import com.api.dto.StoreCategoryDto;
import com.api.dto.StoreDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Device;
import com.api.entity.Group;
import com.api.entity.Item;
import com.api.entity.Organization;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Request;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.StoreCategory;
import com.api.entity.User;

public interface MapStructMapper {
	
	UserDto userToUserDto(User user);
	
	AddressDto addressToAddressDto(Address address);
	
	GroupDto groupToGroupDto(Group group);
	
	List<GroupDto> lstGroupToGroupDto(List<Group> lstGroup);
	
	OrganizationDto organizationToOrganizationDto(Organization organization);
	
	RequestDto requestToRequestDto(Request request);
	
	List<RequestDto> lstRequestToRequestDto(List<Request> lstRequests);
	
	ItemDto itemToItemDto(Item item);
	
	List<ItemDto> lstitemToItemDto(List<Item> lstItem);
	
	ReliefPointDto reliefPointToreliefPointDto(ReliefPoint reliefPoint);
	
	ReliefInformationDto reliefInforToReliefInforDto(ReliefInformation reliefInfor);
	
	List<ReliefPointDto> lstReliefPointToreliefPointDto(List<ReliefPoint> reliefPoint);
	
	List<StoreDto> lstStoreToStoreDto(List<Store> store);
	
	User userDtoToUser(UserDto userDto);
	
	Address addressDtoToAddress(AddressDto addressDto);
	
	Group groupDtoToGroup(GroupDto group);
	
	List<Group> lstGroupDtoToGroup(List<GroupDto> lstGroupDto);
	
	Organization organizationDtoToOrganization(OrganizationDto organization);
	
	Item itemDtoToItem(ItemDto itemDto);
	
	List<Item> lstItemDtoToItem(List<ItemDto> lstItemDto);
	
	ReliefPoint reliefPointDtoToreliefPoint(ReliefPointDto reliefPointDto);
	
	ReliefInformation reliefInforDtoToReliefInfor(ReliefInformationDto reliefInforDto);

	StoreDto storeToStoreDTO(Store store);
	
	Store storeDtoToStore(StoreDto dto);
	
	List<StoreCategoryDto> lstStoreCateToStoreCateDto(List<StoreCategory> lstCate);
	
	List<StoreCategory> lstStoreCateDtoToStoreCate(List<StoreCategoryDto> lstCateDto);
	
	StoreCategoryDto cateToCateDto(StoreCategory cate);
	
	StoreCategory cateDtoToCate(StoreCategoryDto cateDto);
	
	DeviceDto deviceToDeviceDto(Device device);
	
	Device deviceDtoToDevice(DeviceDto deviceDto);
	
	SOSDto SOSToSOSDto(SOS sos);
	
	SOS SOSDtoToSOS(SOSDto sosDto);
	
	
}
