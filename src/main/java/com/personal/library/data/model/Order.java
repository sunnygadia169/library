package com.personal.library.data.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.personal.library.request.model.PlaceOrderRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "customerOrder")
public class Order {
	@Id
	private String id;

	@NotBlank
	@Email(message = "Please enter valid email ID")
	private String customerEmailId;

	@Positive(message = "Please enter valid book quantity")
	private Integer bookQty;

	@Positive
	private Long bookIsbnNo;

	@Positive
	private Double price;

	private LocalDate orderDate;

	public Order(String customerEmailId, Integer bookQty, Long bookIsbnNo, LocalDate orderDate) {
		super();
		this.customerEmailId = customerEmailId;
		this.bookQty = bookQty;
		this.bookIsbnNo = bookIsbnNo;
		this.orderDate = orderDate;
	}

	public Order(PlaceOrderRequest orderReq) {
		this(orderReq.getCustomerEmailId(), orderReq.getBookQty(), orderReq.getBookIsbnNo(), LocalDate.now());
	}

}
