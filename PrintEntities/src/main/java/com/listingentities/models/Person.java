package com.listingentities.models;

import java.util.Collection;
import java.util.List;

public class Person {
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddres;
	
	
	public Person(String firstName,String lastName,String emailAdress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddres = emailAdress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddres() {
		return emailAddres;
	}

	public void setEmailAddres(String emailAddres) {
		this.emailAddres = emailAddres;
	}
	

}
