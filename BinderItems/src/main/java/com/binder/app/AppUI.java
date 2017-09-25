package com.binder.app;

import com.binder.models.Person;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class AppUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout verticalLayout = new VerticalLayout();
		Person person = new Person("John", "Doe");
		Binder<Person> binder = new Binder<>();
		TextField titleField = new TextField();
		binder.forField(titleField).bind(Person::getFirstName, Person::setFirstName);
		TextField nameField = new TextField();
		Button button = new Button("SAve");
		binder.bind(nameField, Person::getLastName,Person::setLastName);
		binder.readBean(person);
		verticalLayout.addComponents(titleField,nameField,button);
		setContent(verticalLayout);
	}

}
