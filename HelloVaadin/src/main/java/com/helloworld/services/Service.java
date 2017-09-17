package com.helloworld.services;

import org.springframework.stereotype.Component;

@Component
public class Service {

	public String sayHello(String name) {
		return "Hello, " + name;
	}
	public String printInfoName(String name) {
		return "Your name is " + name;
	}
}
