package com.api.service;

import com.api.dto.AddressDto;

public interface AddressService {
	
	AddressDto getAddressById();
	
	AddressDto saveAddress();
	
	AddressDto updateAddress();
	
	
}
