package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Sale.SaleCreateDtoRequest;
import com.accio.point_sale.domain.dtos.Sale.SaleDtoResponse;
import com.accio.point_sale.domain.dtos.User.UserDtoResponse;
import com.accio.point_sale.domain.entities.Sale;
import com.accio.point_sale.domain.entities.User;
import com.accio.point_sale.mappers.SaleMapper;
import com.accio.point_sale.services.Sale.SaleService;
import com.accio.point_sale.services.User.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

	private final SaleService saleService;
	private final UserService userService;
	private final SaleMapper saleMapper;

	@Operation(summary = "Get all sales", description = "Retrieve a list of all sales")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of sales retrieved successfully", content = @Content(schema = @Schema(implementation = SaleDtoResponse.class))),
			@ApiResponse(responseCode = "404", description = "No sales found", content = @Content(schema = @Schema()))
	})
	@GetMapping()
	public ResponseEntity<List<SaleDtoResponse>> getAllSales() {
		List<Sale> sales = saleService.getAllSales();

		return ResponseEntity.ok(sales.stream().map(saleMapper::toDtoResponse).toList());
	}

	@Operation(summary = "Create a new sale", description = "Add a new sale to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sale created successfully", content = @Content(schema = @Schema(implementation = SaleDtoResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping
	public ResponseEntity<SaleDtoResponse> create(@RequestBody SaleCreateDtoRequest saleRequest) {
		User user = userService.findById(saleRequest.getUserID());
		Sale sale = saleService.createSale(saleRequest, user);

		return ResponseEntity.ok(saleMapper.toDtoResponse(sale));
	}

}
