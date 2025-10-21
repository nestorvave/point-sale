package com.accio.point_sale.services.Customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accio.point_sale.domain.entities.Customer;
import com.accio.point_sale.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> createBulkCustomers(List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

}
