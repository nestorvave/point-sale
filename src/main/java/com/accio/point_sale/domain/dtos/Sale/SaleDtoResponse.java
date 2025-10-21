package com.accio.point_sale.domain.dtos.Sale;

import java.util.List;
import java.util.UUID;

import com.accio.point_sale.domain.dtos.SaleItem.SaleItemDtoResponse;
import com.accio.point_sale.domain.dtos.User.UserDtoResponse;
import com.accio.point_sale.domain.dtos.Customer.CustomerResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDtoResponse {
	
	private UUID saleID;
	private List<SaleItemDtoResponse> saleItems;
	private UserDtoResponse user;
	private CustomerResponseDto customer;

}
