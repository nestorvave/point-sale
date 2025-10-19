package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Sale.SaleCreateDtoRequest;
import com.accio.point_sale.domain.dtos.Sale.SaleDtoResponse;
import com.accio.point_sale.domain.entities.Sale.Sale;
import com.accio.point_sale.domain.entities.User.User;
import com.accio.point_sale.mappers.Sale.SaleMapper;
import com.accio.point_sale.services.Sale.SaleService;
import com.accio.point_sale.services.User.UserService;

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

	@GetMapping()
	public ResponseEntity<List<SaleDtoResponse>> getAllSales() {
		List<Sale> sales = saleService.getAllSales();

		return ResponseEntity.ok(sales.stream().map(saleMapper::toDtoResponse).toList());
	}

	@PostMapping
	public ResponseEntity<SaleDtoResponse> create(@RequestBody SaleCreateDtoRequest saleRequest) {
		User user = userService.findById(saleRequest.getUserID());
		Sale sale = saleService.createSale(saleRequest, user);

		return ResponseEntity.ok(saleMapper.toDtoResponse(sale));
	}

}
