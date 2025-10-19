package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Product.ProductDto;
import com.accio.point_sale.domain.dtos.User.UserDtoRequest;
import com.accio.point_sale.domain.dtos.User.UserDtoResponse;
import com.accio.point_sale.mappers.UserMapper;
import com.accio.point_sale.services.User.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	@Operation(summary = "Get all users", description = "Retrieve a list of all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of users retrieved successfully", content = @Content(schema = @Schema(implementation = UserDtoResponse.class))),
			@ApiResponse(responseCode = "404", description = "No users found", content = @Content(schema = @Schema()))
	})
	@GetMapping
	public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers().stream().map(e -> userMapper.toDto(e)).toList());
	}

	@Operation(summary = "Create a new user", description = "Add a new user to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserDtoRequest.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping
	public ResponseEntity<UserDtoResponse> create(@RequestBody UserDtoRequest userDtoRequest) {
		return ResponseEntity.ok(userMapper.toDto(userService.createUser(userMapper.toEntity(userDtoRequest))));
	}

}
