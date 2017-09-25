package com.listingentities.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.listingentities.models.Person;

@Component
public class PersonService {
	
	private Collection<Person> personCollection = new HashSet<>();

	public List<Person> getPersons(){
		return new ArrayList<Person>();
	}
	public Collection<Person> addPersons() {
		this.personCollection.addAll(Arrays.asList(new Person("Stamat", "Dobrev"),
				new Person("Unufri", "Godjev"),
				new Person("Julia", "Wayne")));
		return new HashSet<>();
	}
}
