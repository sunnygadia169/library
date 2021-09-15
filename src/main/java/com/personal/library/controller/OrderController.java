package com.personal.library.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.library.data.model.Order;
import com.personal.library.request.model.PlaceOrderRequest;
import com.personal.library.service.OrderService;
import com.personal.library.util.Constants;
import com.personal.library.util.GenericResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse createOrder(@RequestBody @Valid PlaceOrderRequest request) {
		GenericResponse response = new GenericResponse();
		try {
			response.setData(orderService.add(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}

	@GetMapping(path = "/get/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse getOrderById(@PathVariable final String orderId) {
		GenericResponse<Order> response = new GenericResponse();
		try {
			response.setData(orderService.findOrderById(orderId));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}

	@GetMapping("/forInterval")
	public GenericResponse findOrdersByDate(
			@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		GenericResponse<List<Order>> response = new GenericResponse();
		try {
			response.setData(orderService.findAllByDate(startDate, endDate));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}
}
