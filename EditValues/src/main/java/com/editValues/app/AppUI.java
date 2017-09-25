package com.editValues.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.editValues.services.PrintService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class AppUI extends UI{
	
	@Autowired
	PrintService printService;

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		TextField textField = new TextField("Enter your name");
		Button button = new Button("Click me");
		button.addClickListener(e->Notification.show(printService.printHello(textField.getValue())));
		layout.addComponents(textField,button);
		setContent(layout);
	}

}
