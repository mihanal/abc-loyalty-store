package com.abc.ecom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.abc.ecom.dto.CustomerDTO;
import com.abc.ecom.model.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {
	
	@Mapping(target = "customerId", source = "userId")
	CustomerDTO toCustomerDTO(Customer customer);
}
