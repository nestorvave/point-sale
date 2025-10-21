package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Customer.CustomerRequestDto;
import com.accio.point_sale.domain.dtos.Customer.CustomerResponseDto;
import com.accio.point_sale.domain.entities.Customer;
import com.accio.point_sale.mappers.CustomerMapper;
import com.accio.point_sale.services.Customer.CustomerService;

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

	@GetMapping()
	public ResponseEntity<List<CustomerResponseDto>> getAll() {
		List<Customer> listCustomers = customerService.getAllCustomers();

		return ResponseEntity.ok(listCustomers.stream()
				.map(customerMapper::toDto)
				.toList());
	}

	@PostMapping()
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
		Customer customerEntity = customerMapper.toEntity(customerRequestDto);
		Customer customerResponse = customerService.createCustomer(customerEntity);

		return new ResponseEntity<>(customerMapper.toDto(customerResponse), HttpStatus.CREATED);
	}

	@PostMapping("/bulk")
	public ResponseEntity<List<CustomerResponseDto>> bulkCreateCustomers(
			@RequestBody List<CustomerRequestDto> listCustomers) {
		List<Customer> listCustomersEntity = listCustomers.stream().map(customerMapper::toEntity).toList();
		List<Customer> listCustomersResponse = customerService.createBulkCustomers(listCustomersEntity);

		return new ResponseEntity<>(listCustomersResponse.stream().map(customerMapper::toDto).toList(), HttpStatus.CREATED);
	}

}
