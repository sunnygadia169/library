package com.personal.library.request.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PlaceOrderRequest {

	@NotBlank
	@Email(message = "Please enter valid email ID")
	private String customerEmailId;

	@Positive(message = "Please enter valid book quantity")
	private Integer bookQty;

	@Positive
	private Long bookIsbnNo;

}
