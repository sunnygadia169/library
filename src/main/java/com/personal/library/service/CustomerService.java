/**
 * 
 */
package com.personal.library.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.library.data.model.Customer;
import com.personal.library.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Sunny_Gadia
 *
 */
@RequiredArgsConstructor
@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	public Customer add(Customer customer) throws Exception {
		Optional<Customer> existingCustomer = customerRepository.findCustomerByEmailIdIgnoreCase(customer.getEmailId());

		if (existingCustomer.isPresent()) {
			throw new Exception("Email ID already registered");
		}
		return customerRepository.insert(customer);
	}
}
