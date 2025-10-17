package com.accio.point_sale.services.Product;

import java.util.List;

import com.accio.point_sale.domain.entities.Product.Product;

public interface ProductService {

	Product createProduct(Product product);

	List<Product> getAllProducts();

}
