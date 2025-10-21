package com.accio.point_sale.services.Customer;

import java.util.List;

import com.accio.point_sale.domain.entities.Customer;

public interface CustomerService {
	
	Customer createCustomer(Customer customer);

	List<Customer> getAllCustomers();

	List<Customer> createBulkCustomers(List<Customer> customers);
}
