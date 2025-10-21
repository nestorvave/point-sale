package com.accio.point_sale.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accio.point_sale.domain.dtos.Product.CreateProductDto;
import com.accio.point_sale.domain.dtos.Product.ProductDto;
import com.accio.point_sale.domain.entities.Product;
import com.accio.point_sale.mappers.ProductMapper;
import com.accio.point_sale.services.Product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {

	private final ProductService productService;
	private final ProductMapper productMapper;
	

	@Operation(summary = "Get all products", description = "Retrieve a list of all products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of products retrieved successfully", content = @Content(schema = @Schema(implementation = ProductDto.class))),
			@ApiResponse(responseCode = "404", description = "No products found", content = @Content(schema = @Schema()))
	})
	@GetMapping()
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = productService.getAllProducts().stream().map(productMapper::toDto).toList();
		return ResponseEntity.ok(products);
	}


	@Operation(summary = "Create a new product", description = "Add a new product to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product created successfully", content = @Content(schema = @Schema(implementation = CreateProductDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping()
	public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto createProductDto) {
		Product product = productService.createProduct(productMapper.toEntity(createProductDto));
		return ResponseEntity.ok(productMapper.toDto(product));
	}

	@Operation(summary = "Create a bulk of products", description = "Add a new products to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Products created successfully", content = @Content(schema = @Schema(implementation = CreateProductDto.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(schema = @Schema()))
	})
	@PostMapping("/bulk")
	public ResponseEntity<List<ProductDto>> createBulkProducts(@RequestBody List<CreateProductDto> createProductDto) {
		List<Product> products = productService.updateMultipleProducts(createProductDto.stream().map(productMapper::toEntity).toList());
		return ResponseEntity.ok(products.stream().map(productMapper::toDto).toList());
	}

}
