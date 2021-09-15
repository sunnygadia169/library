package com.personal.library.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.library.data.model.Customer;
import com.personal.library.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	/**
	 * This method save customer object if the email ID is not already registered
	 * @param customer request
	 * @return created object of Customer
	 * @throws Exception If customer email ID is already registerd
	 */
	public Customer add(Customer customer) throws Exception {
		Optional<Customer> existingCustomer = customerRepository.findCustomerByEmailIdIgnoreCase(customer.getEmailId());

		if (existingCustomer.isPresent()) {
			throw new Exception("Email ID already registered");
		}
		return customerRepository.insert(customer);
	}
}
