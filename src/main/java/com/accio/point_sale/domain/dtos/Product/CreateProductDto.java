package com.accio.point_sale.domain.dtos.Product;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto {

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Description is required")
	private String description;

	@NotNull(message = "Price is required")
	@Builder.Default
	private BigDecimal price = BigDecimal.ZERO;

	@Schema(description = "Product stock, optional", example = "0", required = false)
	@Builder.Default
	private Integer stock = 0;

}
