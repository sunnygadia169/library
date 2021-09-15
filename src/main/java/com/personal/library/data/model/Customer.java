package com.personal.library.data.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "customer")
public class Customer {
	@Id
	private String id;

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	
	@Indexed(unique = true)
	@NotBlank
	@Email(message="Please enter valid email ID")
	private String emailId;

	public Customer(@NotBlank String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

}
