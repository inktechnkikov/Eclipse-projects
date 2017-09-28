package com.dependencyinjection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dependencyinjection.interfaces.GreetingService;

@Component
public class HomeController {
	
	@Autowired
	private GreetingService greetingService;
	
	@GetMapping("/")
	@ResponseBody
	public String getGreetingPage() {
		return greetingService.greeting();
	}
	@GetMapping("/french")
	@ResponseBody
	public String getFrenchGreetingPage() {
		return greetingService.greeting();
	}
}
