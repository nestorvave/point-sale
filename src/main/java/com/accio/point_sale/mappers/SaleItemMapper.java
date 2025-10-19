package com.accio.point_sale.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.accio.point_sale.domain.dtos.SaleItem.SaleItemDtoResponse;
import com.accio.point_sale.domain.entities.SaleItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleItemMapper {

	@Mapping(source = "product.id", target = "productID")
	@Mapping(source = "product.name", target = "productName")
	@Mapping(target = "totalAmount", expression = "java(saleItem.getUnitPrice().multiply(java.math.BigDecimal.valueOf(saleItem.getQuantity())))")
	SaleItemDtoResponse toDtoResponse(SaleItem saleItem);

	@Named("calculateTotalAmount")
	default BigDecimal calculateTotalAmount(SaleItem item) {
		if (item == null || item.getUnitPrice() == null || item.getQuantity() == null) {
			return BigDecimal.ZERO;
		}
		return item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
	}
}
