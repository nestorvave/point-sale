package com.accio.point_sale.mappers.Product;

import com.accio.point_sale.domain.dtos.Product.CreateProductDto;
import com.accio.point_sale.domain.dtos.Product.ProductDto;
import com.accio.point_sale.domain.entities.Product.Product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

	ProductDto toDto(Product entity);

	Product toEntity(ProductDto dto);

	Product toEntity(CreateProductDto dto); 
}