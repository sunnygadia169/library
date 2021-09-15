package com.personal.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.library.data.model.Order;
import com.personal.library.repository.OrderRepository;
import com.personal.library.request.model.FetchCustomerMonthlySummaryRequest;
import com.personal.library.response.model.CustomerOrderSummaryResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsService {
	private final OrderRepository orderRepository;

	public List<CustomerOrderSummaryResponse> getCustomerOrderMonthlySummary(
			FetchCustomerMonthlySummaryRequest request) {
		List<CustomerOrderSummaryResponse> result = new ArrayList();
		Map<String, List<Order>> monthlyOrders = orderRepository
				.findAllOrdersByCustomerEmailIdIgnoreCase(request.getCustomerEmailId()).stream()
				.collect(Collectors.groupingBy(x -> x.getOrderDate().getMonth().toString()));

		for (Map.Entry<String, List<Order>> entry : monthlyOrders.entrySet()) {
			CustomerOrderSummaryResponse customerOrderSummary = new CustomerOrderSummaryResponse();
			customerOrderSummary.setMonth(entry.getKey());
			customerOrderSummary.setTotalOrderQty((long) entry.getValue().size());
			customerOrderSummary.setTotalBookQty(entry.getValue().stream().mapToLong(Order::getBookQty).sum());
			customerOrderSummary.setTotalAmount(entry.getValue().stream().mapToDouble(Order::getPrice).sum());
			
			result.add(customerOrderSummary);
		}

		return result;
	}
}
