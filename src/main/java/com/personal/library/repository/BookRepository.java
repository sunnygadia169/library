package com.personal.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.personal.library.data.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
	Optional<Book> findBookByIsbnNo(Long bookIsbnNo);
}
