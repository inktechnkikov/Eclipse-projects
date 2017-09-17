package com.helloworld.vaadinui;

import org.springframework.beans.factory.annotation.Autowired;

import com.helloworld.services.Service;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI{
	
	@Autowired
	private Service service;
	
	@Override
	protected void init(VaadinRequest request) {
		TextField textField = new TextField("Insert your name");
		Button button = new Button("Send");
		Button button2 = new Button("Greeting");
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponents(textField,button,button2);
		
		button.addClickListener(e -> Notification.show(service.sayHello(textField.getValue())));
		button2.addClickListener(e1 -> Notification.show(service.printInfoName(textField.getValue())));
		setContent(verticalLayout);
	}	

}
