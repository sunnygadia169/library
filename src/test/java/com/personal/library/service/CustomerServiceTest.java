package com.personal.library.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.personal.library.LibraryApplication;
import com.personal.library.data.model.Customer;
import com.personal.library.repository.CustomerRepository;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CustomerServiceTest {

	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@BeforeEach
	void initBefore() {
//		ReflectionTestUtils.setField(customerService, "customerRepository", customerRepository);
		customerService = new CustomerService(customerRepository);
	}

//	@Test
	void testAddCustomer() {
		Customer newCustomer = new Customer("Sunny", "Gadia", "test@xyz.com");
		Customer register = null;
		try {
			register = customerService.add(newCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(register);
	}

}