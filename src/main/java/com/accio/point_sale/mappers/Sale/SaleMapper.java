package com.accio.point_sale.mappers.Sale;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.accio.point_sale.domain.dtos.Sale.SaleDtoResponse;
import com.accio.point_sale.domain.entities.Sale.Sale;
import com.accio.point_sale.mappers.SaleItem.SaleItemMapper;
import com.accio.point_sale.mappers.User.UserMapper;

@Mapper(componentModel = "spring", uses = { SaleItemMapper.class, UserMapper.class })
public interface SaleMapper {

	@Mapping(target = "saleID", source = "id")
	SaleDtoResponse toDtoResponse(Sale sale);
}