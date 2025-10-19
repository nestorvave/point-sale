package com.accio.point_sale.services.Product;

import java.util.List;
import java.util.UUID;

import com.accio.point_sale.domain.entities.Product;

public interface ProductService {

	Product createProduct(Product product);

	List<Product> getAllProducts();

	Product getProductById(UUID id);

}
