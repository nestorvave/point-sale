package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Customer.CustomerRequestDto;
import com.accio.point_sale.domain.dtos.Customer.CustomerResponseDto;
import com.accio.point_sale.domain.entities.Customer;
import com.accio.point_sale.mappers.CustomerMapper;
import com.accio.point_sale.services.Customer.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;
	private final CustomerMapper customerMapper;

	@Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of customers retrieved successfully", content = @Content(schema = @Schema(implementation = CustomerResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "No customers found", content = @Content(schema = @Schema()))
	})
	@GetMapping()
	public ResponseEntity<List<CustomerResponseDto>> getAll() {
		List<Customer> listCustomers = customerService.getAllCustomers();

		return ResponseEntity.ok(listCustomers.stream()
				.map(customerMapper::toDto)
				.toList());
	}

	@Operation(summary = "Create a new customer", description = "Add a new customer to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customer created successfully", content = @Content(schema = @Schema(implementation = CustomerRequestDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping()
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
		Customer customerEntity = customerMapper.toEntity(customerRequestDto);
		Customer customerResponse = customerService.createCustomer(customerEntity);

		return new ResponseEntity<>(customerMapper.toDto(customerResponse), HttpStatus.CREATED);
	}

	@Operation(summary = "Create a bulk of customers", description = "Add a new customers to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Customers created successfully", content = @Content(schema = @Schema(implementation = CustomerRequestDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping("/bulk")
	public ResponseEntity<List<CustomerResponseDto>> bulkCreateCustomers(
			@RequestBody List<CustomerRequestDto> listCustomers) {
		List<Customer> listCustomersEntity = listCustomers.stream().map(customerMapper::toEntity).toList();
		List<Customer> listCustomersResponse = customerService.createBulkCustomers(listCustomersEntity);

		return new ResponseEntity<>(listCustomersResponse.stream().map(customerMapper::toDto).toList(), HttpStatus.CREATED);
	}

}
