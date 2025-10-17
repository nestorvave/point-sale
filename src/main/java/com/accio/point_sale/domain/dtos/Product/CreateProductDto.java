package com.accio.point_sale.domain.dtos.Product;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto {

	private String name;

	private String description;

	@Builder.Default
	private BigDecimal price = BigDecimal.ZERO;

	@Schema(description = "Product stock, optional", example = "0", required = false)
	@Builder.Default
	private Integer stock = 0;

}
