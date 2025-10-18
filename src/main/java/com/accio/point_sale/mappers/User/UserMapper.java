package com.accio.point_sale.mappers.User;

import com.accio.point_sale.domain.dtos.User.UserDtoRequest;
import com.accio.point_sale.domain.dtos.User.UserDtoResponse;

import com.accio.point_sale.domain.entities.User.User;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	UserDtoResponse toDto(User entity);

	User toEntity(UserDtoRequest dto);

}