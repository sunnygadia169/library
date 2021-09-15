package com.personal.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.library.data.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	Optional<Customer> findCustomerByEmailIdIgnoreCase(String emailId);
}
