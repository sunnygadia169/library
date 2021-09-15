package com.personal.library.response.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderSummaryResponse {
	private String month;
	private Long totalOrderQty;
	private Long totalBookQty;
	private Double totalAmount;
}
