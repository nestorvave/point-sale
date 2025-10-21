package com.accio.point_sale.domain.dtos.Customer;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

	private UUID id;

	private String fullName;

	private String email;

	private String phone;

}
