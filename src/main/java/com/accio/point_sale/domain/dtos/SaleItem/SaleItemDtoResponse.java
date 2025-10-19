package com.accio.point_sale.domain.dtos.SaleItem;

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
public class SaleItemDtoResponse {

	private UUID id;
	private UUID productID;
	private Integer quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalAmount;
	private String productName;
}
