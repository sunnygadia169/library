package com.personal.library.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.library.data.model.Book;
import com.personal.library.request.model.UpdateBookStockRequest;
import com.personal.library.service.BookService;
import com.personal.library.util.Constants;
import com.personal.library.util.GenericResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

	private final BookService bookService;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse createBook(@RequestBody @Valid Book request) {
		GenericResponse<Book> response = new GenericResponse();
		try {
			response.setData(bookService.add(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}

	@PostMapping(path = "/updateStock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updateStock(@RequestBody @Valid UpdateBookStockRequest request) {
		GenericResponse<String> response = new GenericResponse();
		try {
			response.setData(bookService.updateStock(request));
			response.setStatus(Constants.RESPONSE_STATUS_OK);
			response.setMessage(Constants.RESPONSE_MESSAGE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Constants.RESPONSE_STATUS_FAIL);
		}
		return response;
	}
}
