package com.binder.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import com.binder.models.Person;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class AppUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout verticalLayout = new VerticalLayout();
		setContent(verticalLayout);
		Collection<Person> persons = new HashSet<Person>(Arrays.asList(new Person("Stamat", "Stamatov"),
				new Person("Clark", "Ken")));
		Grid<Person> personGrid = new Grid<Person>(Person.class);
		personGrid.setItems(persons);
		verticalLayout.addComponent(personGrid);
		
	}

}
