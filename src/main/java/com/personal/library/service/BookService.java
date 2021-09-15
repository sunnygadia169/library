package com.personal.library.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.personal.library.data.model.Book;
import com.personal.library.repository.BookRepository;
import com.personal.library.request.model.UpdateBookStockRequest;
import com.personal.library.util.Constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {
	private final BookRepository bookRepository;

	public Book add(Book bookData) {
		Book result = bookRepository.save(bookData);
		return result;
	}

	public String updateStock(UpdateBookStockRequest request) throws Exception {
		String status = Constants.FAIL;

		Optional<Book> book = bookRepository.findBookByIsbnNo(request.getIsbnNo());
		if (book.isPresent()) {
			book.get().setStockQty(request.getStockQty());
			bookRepository.save(book.get());
			status = Constants.SUCCESS;
		} else {
			throw new Exception(
					"Error in updating stock for book with ISBN Number as No book present for the given ISBN Number");
		}

		return status;
	}
}
