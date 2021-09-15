package com.personal.library.request.model;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateBookStockRequest {
	@PositiveOrZero
	(message="Please enter a valid stock quantity")
	public Integer stockQty;

	@Positive(message="Please enter a valid ISBN number")
	private Long isbnNo;
}
