package com.listingentities.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.listingentities.models.Person;
import com.listingentities.services.PersonService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class AppUI extends UI{
	
	@Autowired
	private PersonService personService;
	
	@Override
	protected void init(VaadinRequest request) {
		
		VerticalLayout verticalLayout = new VerticalLayout();
	/*	Person person = new Person("Stamat", "Dobrev");
		Person person1 = new Person("Momo", "Kolev");
		Person person2 = new Person("Grandi", "Robinson");
		Person person3 = new Person("Julia", "Rols");
		List<Person> persons = new ArrayList<>();
		*/
		
		Grid<Person> grid = new Grid<Person>();
		grid.addColumn(Person::getFirstName).setCaption("First name");
		grid.addColumn(Person::getLastName).setCaption("Last name");
		 //personService.addPersons();
		grid.setItems(hashSet);
		verticalLayout.addComponents(grid);
		setContent(verticalLayout);
	}

}
