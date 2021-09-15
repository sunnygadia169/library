package com.personal.library.data.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "book")
public class Book {
	@Id
	private String id;

	@NotBlank(message = "Please enter valid name")
	private String name;

	@Positive
	private Double price;
	
	@PositiveOrZero
	private Integer stockQty;

	@Indexed(unique = true)
	@Positive
	private Long isbnNo;

	public Book(String name, Integer stockQty, Long isbnNo, Double price) {
		super();
		this.name = name;
		this.stockQty = stockQty;
		this.isbnNo = isbnNo;
		this.price = price;
	}

}
