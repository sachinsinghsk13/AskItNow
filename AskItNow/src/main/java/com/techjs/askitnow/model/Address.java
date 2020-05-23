package com.techjs.askitnow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "house_and_street")
	private String houseAndStreet;
	private String locality;
	private String city;
	private String state;
	private String zipcode;
	
	public String getHouseAndStreet() {
		return houseAndStreet;
	}
	public void setHouseAndStreet(String houseAndStreet) {
		this.houseAndStreet = houseAndStreet;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
