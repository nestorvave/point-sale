package com.accio.point_sale.domain.dtos.User;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoResponse {

	private UUID id;

	private String name;

	private String email;


}
