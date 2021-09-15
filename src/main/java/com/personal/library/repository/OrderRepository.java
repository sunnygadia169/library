package com.personal.library.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.library.data.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findAllOrdersByCustomerEmailIdIgnoreCase(String customerEmailId);

	Page<Order> findOrderByCustomerEmailIdIgnoreCase(String customerEmailId, Pageable page);

	List<Order> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

}
