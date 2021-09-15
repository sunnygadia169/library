package com.personal.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.library.request.model.FetchCustomerMonthlySummaryRequest;
import com.personal.library.response.model.CustomerOrderSummaryResponse;
import com.personal.library.service.StatisticsService;
import com.personal.library.util.Constants;
import com.personal.library.util.GenericResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

	private final StatisticsService statisticsService;


	@PostMapping(path = "/fetchCustomerMonthlyStats", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse fetchCustomerMonthlyStats(@RequestBody @Valid FetchCustomerMonthlySummaryRequest request) {
		GenericResponse<List<CustomerOrderSummaryResponse>> response = new GenericResponse();
		try {
			response.setData(statisticsService.getCustomerOrderMonthlySummary(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}
}
