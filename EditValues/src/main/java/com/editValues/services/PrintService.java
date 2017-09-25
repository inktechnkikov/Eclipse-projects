package com.editValues.services;

import org.springframework.stereotype.Component;

@Component
public class PrintService {

	public String printHello(String name) {
		return "Hello " + name;
	}
}
