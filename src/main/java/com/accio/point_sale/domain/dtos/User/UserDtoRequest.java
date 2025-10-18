package com.accio.point_sale.domain.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoRequest {

	private String name;

	private String email;

	private String password;
}
