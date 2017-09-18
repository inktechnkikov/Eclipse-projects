package com.listingentities.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.listingentities.models.Person;

@Component
public class PersonService {

	public List<Person> getPeople(){
		return new ArrayList<Person>();
	}
}
