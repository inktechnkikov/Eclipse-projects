package com.dependencyinjection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dependencyinjection.interfaces.GreetingService;

@Controller
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
