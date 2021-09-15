package com.personal.library.request.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FetchCustomerOrderRequest {


	@NotBlank
	@Email(message = "Please enter valid email ID")
	private String customerEmailId;

	private boolean pageAble;

	@PositiveOrZero(message = "Please enter valid book quantity")
	private int pageNo;

	@PositiveOrZero
	private int pageSize;

}
