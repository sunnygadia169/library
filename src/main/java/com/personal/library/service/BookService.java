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

	/**
	 * This method helps in adding a new book if ISBN number is not already registered.
	 * 
	 * @param bookData - contains book related details like ISBN no, price, stock
	 *                 quantity and name
	 * @return object saved for the Book
	 */
	public Book add(Book bookData) throws Exception {
		Optional<Book> book = bookRepository.findBookByIsbnNo(bookData.getIsbnNo());
		if (book.isPresent()) {
			throw new Exception("Book ISBN Number already registered");
		}
		
		return bookRepository.save(bookData);
	}

	/**
	 * This method helps in adding stock of an existing book and returns status
	 * 
	 * @param request - contains data like book ISBN no and the stock quantity that
	 *                needs to be added to the existing stock
	 * @return - The updated Book object
	 * @throws Exception
	 */
	public String addStock(UpdateBookStockRequest request) throws Exception {
		String status = Constants.FAIL;

		Optional<Book> book = bookRepository.findBookByIsbnNo(request.getIsbnNo());
		if (book.isPresent()) {
			Book currBook = book.get();
			currBook.setStockQty(request.getStockQty() + currBook.getStockQty());
			bookRepository.save(currBook);
			status = Constants.SUCCESS;
		} else {
			throw new Exception(
					"Error in updating stock for book with ISBN Number as No book present for the given ISBN Number");
		}

		return status;
	}
}
