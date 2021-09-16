package com.personal.library.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.personal.library.LibraryApplication;
import com.personal.library.data.model.Customer;
import com.personal.library.repository.CustomerRepository;

@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
public class CustomerServiceTest {

	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@BeforeEach
	void initBefore() {
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