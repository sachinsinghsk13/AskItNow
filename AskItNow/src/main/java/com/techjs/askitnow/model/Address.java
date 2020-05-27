package com.techjs.askitnow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Embeddable
public class Address {
	
	@Column(name = "house_and_street")
	private String houseAndStreet;
	private String locality;
	
	@Size(min = 2, max = 20, message = "city name length must not be greater than 20")
	private String city;
	
	@Size(max = 20, message = "state name length must not be greater than 15")
	private String state;
	
	@Size(min = 6, max = 6, message = "zipcode must be a number of six digits")
	private String zipcode;
	
}
