import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.personal.library.LibraryApplication;
import com.personal.library.data.model.Customer;
import com.personal.library.repository.CustomerRepository;
import com.personal.library.request.model.FetchCustomerOrderRequest;
import com.personal.library.service.CustomerService;
import com.personal.library.util.Constants;
import com.personal.library.util.GenericResponse;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void initBefore() {
		ReflectionTestUtils.setField(customerService, "customerRepository", customerRepository);
	}

	@Test
	void testCreateCustomer() {
		Customer newCustomer = new Customer("Sunny", "Gadia", "test@gmai.com");
		ResponseEntity<GenericResponse> postResponse = restTemplate
				.postForEntity("http://localhost:8085/api/customer/create", newCustomer, GenericResponse.class);
		assertNotNull(postResponse);
		assertEquals(Constants.RESPONSE_STATUS_OK, postResponse.getBody().getStatus());
		LinkedHashMap map = (LinkedHashMap) postResponse.getBody().getData();
		assertEquals("Sunny", (map.get("firstName").toString()));
	}

	@Test
	void testFetchCustomerOrders() {
		FetchCustomerOrderRequest request = new FetchCustomerOrderRequest();
		request.setCustomerEmailId("test@gmai.com");
		request.setPageAble(false);
		ResponseEntity<GenericResponse> postResponse = restTemplate
				.postForEntity("http://localhost:8085/api/customer/fetchOrders", request, GenericResponse.class);
		assertNotNull(postResponse);
		assertEquals(Constants.RESPONSE_STATUS_OK, postResponse.getBody().getStatus());
		assertEquals(0, ((List) postResponse.getBody().getData()).size());
	}

	/*
	@Test
	void testCreateAndFetchCustomerOrders() {
		Customer newCustomer = new Customer("Sunny", "Gadia", "test@gmai.com");
		ResponseEntity<GenericResponse> postResponse1 = restTemplate
				.postForEntity("http://localhost:8085/api/customer/create", newCustomer, GenericResponse.class);

		FetchCustomerOrderRequest request = new FetchCustomerOrderRequest();
		request.setCustomerEmailId("test@gmai.com");
		request.setPageAble(false);
		ResponseEntity<GenericResponse> postResponse = restTemplate
				.postForEntity("http://localhost:8085/api/customer/fetchOrders", request, GenericResponse.class);
		assertNotNull(postResponse);
		assertEquals(Constants.RESPONSE_STATUS_OK, postResponse.getBody().getStatus());
		assertEquals(1, ((List) postResponse.getBody().getData()).size());
	}
	

	@Test
	void testCreateAndFetchCustomerOrdersWithPagination() {
		Customer newCustomer = new Customer("Sunny", "Gadia", "test@gmai.com");
		ResponseEntity<GenericResponse> postResponse1 = restTemplate
				.postForEntity("http://localhost:8085/api/customer/create", newCustomer, GenericResponse.class);

		FetchCustomerOrderRequest request = new FetchCustomerOrderRequest();
		request.setCustomerEmailId("test@gmai.com");
		request.setPageAble(true);
		request.setPageNo(0);
		request.setPageSize(1);
		ResponseEntity<GenericResponse> postResponse = restTemplate
				.postForEntity("http://localhost:8085/api/customer/fetchOrders", request, GenericResponse.class);
		assertNotNull(postResponse);
		assertEquals(Constants.RESPONSE_STATUS_OK, postResponse.getBody().getStatus());
		assertEquals(1, ((List) postResponse.getBody().getData()).size());
	}
	
	*/
}
