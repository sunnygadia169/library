package com.personal.library.request.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FetchCustomerMonthlySummaryRequest {

	@NotBlank
	@Email(message = "Please enter valid email ID")
	private String customerEmailId;
	
}
