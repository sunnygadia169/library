package com.personal.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.library.data.model.Customer;
import com.personal.library.data.model.Order;
import com.personal.library.request.model.FetchCustomerOrderRequest;
import com.personal.library.service.CustomerService;
import com.personal.library.service.OrderService;
import com.personal.library.util.Constants;
import com.personal.library.util.GenericResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService customerService;
	private final OrderService orderService;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse createCustomer(@RequestBody @Valid Customer request) {
		GenericResponse<Customer> response = new GenericResponse();
		try {
			response.setData(customerService.add(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}

	@PostMapping(path = "/fetchOrders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse fetchCustomerOrders(@RequestBody @Valid FetchCustomerOrderRequest request) {
		GenericResponse<List<Order>> response = new GenericResponse();
		try {
			response.setData(orderService.fetchCustomerOrders(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}
}
