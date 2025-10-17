package com.accio.point_sale.domain.dtos.Product;

import java.math.BigDecimal;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
	private UUID id;

	private String name;

	private String description;

	@Builder.Default
	private BigDecimal price = BigDecimal.ZERO;

	@Builder.Default
	private Integer stock = 0;

}
