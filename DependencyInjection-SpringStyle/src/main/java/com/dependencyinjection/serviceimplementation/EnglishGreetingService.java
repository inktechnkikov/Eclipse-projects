package com.dependencyinjection.serviceimplementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dependencyinjection.interfaces.GreetingService;

@Primary
public class EnglishGreetingService implements GreetingService{

	@Override
	public String greeting() {
		return "Hello world";
	}

}
