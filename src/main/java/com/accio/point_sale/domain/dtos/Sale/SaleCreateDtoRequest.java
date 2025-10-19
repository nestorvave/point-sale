package com.accio.point_sale.domain.dtos.Sale;

import java.util.List;
import java.util.UUID;

import com.accio.point_sale.domain.dtos.SaleItem.SaleItemCreateDtoRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleCreateDtoRequest {

	@NotNull(message = "User ID is required")
	private UUID userID;

	@NotEmpty(message = "Sale must have at least one item")
	private List<@Valid SaleItemCreateDtoRequest> saleItems;

}
