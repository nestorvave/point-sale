package com.accio.point_sale.services.Sale;

import com.accio.point_sale.domain.dtos.Sale.SaleCreateDtoRequest;
import com.accio.point_sale.domain.dtos.SaleItem.SaleItemCreateDtoRequest;
import com.accio.point_sale.domain.entities.Customer;
import com.accio.point_sale.domain.entities.Product;
import com.accio.point_sale.domain.entities.Sale;
import com.accio.point_sale.domain.entities.SaleItem;
import com.accio.point_sale.domain.entities.User;
import com.accio.point_sale.repositories.SaleRepository;
import com.accio.point_sale.services.Customer.CustomerService;
import com.accio.point_sale.services.Product.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

	private final SaleRepository saleRepository;
	private final ProductService productService;
	private final CustomerService customerService;

	@Override
	@Transactional
	public Sale createSale(SaleCreateDtoRequest saleRequest, User user) {

		// Need Refactor cuz N+1 query ISSUE
		List<Product> products = saleRequest.getSaleItems().stream()
				.map(item -> productService.getProductById(item.getProductID())).toList();

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (SaleItemCreateDtoRequest item : saleRequest.getSaleItems()) {
			Product product = products.stream()
					.filter(p -> p.getId().equals(item.getProductID()))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Product not found"));

			BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			totalAmount = totalAmount.add(subtotal);
		}

		Sale sale = Sale.builder()
				.user(user)
				.createdAt(LocalDateTime.now())
				.totalAmount(totalAmount)
				.build();

		List<Product> productsUpdated = new ArrayList<>();
		List<SaleItem> saleItems = saleRequest.getSaleItems().stream()
				.map(item -> {
					Product product = products.stream()
							.filter(p -> p.getId().equals(item.getProductID()))
							.findFirst()
							.orElseThrow(() -> new RuntimeException("Product not found"));

					if (!product.getIsService()) {
						if (product.getStock() <= 0 || product.getStock() < item.getQuantity()) {
							throw new RuntimeException("Not enough stock");
						}
						product.setStock(product.getStock() - item.getQuantity());
						productsUpdated.add(product);
					}

					return SaleItem.builder()
							.product(product)
							.quantity(item.getQuantity())
							.unitPrice(product.getPrice())
							.sale(sale)
							.build();
				})
				.toList();

		if (saleRequest.getCustomerEmail() != null) {
			Customer customer= customerService.getCustomerByEmail(saleRequest.getCustomerEmail());
			sale.setCustomer(customer);
		}

		sale.setSaleItems(saleItems);

		productService.updateMultipleProducts(productsUpdated);

		return saleRepository.save(sale);

	}

	@Override
	public List<Sale> getAllSales() {
		return saleRepository.findAll();
	}

}
