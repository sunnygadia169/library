package com.personal.library.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.personal.library.data.model.Book;
import com.personal.library.data.model.Customer;
import com.personal.library.data.model.Order;
import com.personal.library.repository.BookRepository;
import com.personal.library.repository.CustomerRepository;
import com.personal.library.repository.OrderRepository;
import com.personal.library.request.model.FetchCustomerOrderRequest;
import com.personal.library.request.model.PlaceOrderRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final BookRepository bookRepository;
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;

	/**
	 * This method checks if the customer id is already registered.
	 * If yes, then it checks for requested book availability.
	 * If all checks are satisfied, updated the book stock and computes the price and saves into order details.
	 * @param orderReq - Request with details like customer email ID, book ISBN no, book quantity
	 * @return Created object of Order type 
	 * @throws Exception
	 */
	public Order add(PlaceOrderRequest orderReq) throws Exception {

		Optional<Book> book = null;
		Order result = null;
		Optional<Customer> customer = customerRepository.findCustomerByEmailIdIgnoreCase(orderReq.getCustomerEmailId());
		if (customer.isPresent()) {
			book = bookRepository.findBookByIsbnNo(orderReq.getBookIsbnNo());
			if (book.isPresent()) {
				Book requestedBook = book.get();
				Integer requestedQty = orderReq.getBookQty();
				Integer availableStock = requestedBook.getStockQty();
				if (requestedQty <= availableStock) {
					Order newOrder = new Order(orderReq);
					newOrder.setPrice(requestedBook.getPrice() * requestedQty);
					result = orderRepository.insert(newOrder);
					requestedBook.setStockQty(availableStock - requestedQty);
					bookRepository.save(requestedBook);
				} else {
					throw new Exception("Requested quantity not available for the given book ISBN Number");
				}
			} else {
				throw new Exception("Book ISBN Number not registered");
			}
		} else {
			throw new Exception("Customer Email ID not registered");
		}

		return result;
	}

	/**
	 * Fetch orders for a customer email ID. ALso, supports pagination
	 * @param request with details like customer email ID, pageable, page no and page size
	 * @return List of Orders
	 */
	public List<Order> fetchCustomerOrders(@Valid FetchCustomerOrderRequest request) {
		List<Order> orderList = null;

		if (request.isPageAble()) {
			Pageable pageData = PageRequest.of(request.getPageNo(), request.getPageSize());
			orderList = orderRepository.findOrderByCustomerEmailIdIgnoreCase(request.getCustomerEmailId(), pageData)
					.getContent();
		} else {
			orderList = orderRepository.findAllOrdersByCustomerEmailIdIgnoreCase(request.getCustomerEmailId());
		}

		return orderList;
	}

	/**
	 * Fetches the order details for an order ID
	 * @param id - Order ID
	 * @return - Order object if present else null
	 */
	public Order findOrderById(String id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.isPresent() ? order.get() : null;
	}

	/**
	 * Fetches all the orders for a given date range
	 * @param startDate - date in "yyyy-mm-dd" format
	 * @param endDate - date in "yyyy-mm-dd" format
	 * @return List of orders in the given date range
	 */
	public List<Order> findAllByDate(LocalDate startDate, LocalDate endDate) {
		List<Order> orderList = orderRepository.findByOrderDateBetween(startDate, endDate);
		return orderList;
	}
}
