package com.accio.point_sale.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.accio.point_sale.domain.dtos.Sale.SaleDtoResponse;
import com.accio.point_sale.domain.entities.Sale;

@Mapper(componentModel = "spring", uses = { SaleItemMapper.class, UserMapper.class, CustomerMapper.class })
public interface SaleMapper {

	@Mapping(target = "saleID", source = "id")
	SaleDtoResponse toDtoResponse(Sale sale);
}