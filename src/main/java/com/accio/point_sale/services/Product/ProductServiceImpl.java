package com.accio.point_sale.services.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accio.point_sale.domain.entities.Product.Product;
import com.accio.point_sale.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


	private final ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
