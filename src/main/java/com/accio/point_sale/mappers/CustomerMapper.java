package com.accio.point_sale.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.accio.point_sale.domain.dtos.Customer.CustomerRequestDto;
import com.accio.point_sale.domain.dtos.Customer.CustomerResponseDto;
import com.accio.point_sale.domain.entities.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

	CustomerResponseDto toDto(Customer customer);

	Customer toEntity(CustomerRequestDto customerRequestDto);

}
