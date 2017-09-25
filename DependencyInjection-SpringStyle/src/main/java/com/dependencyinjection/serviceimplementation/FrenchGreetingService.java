package com.dependencyinjection.serviceimplementation;

import com.dependencyinjection.interfaces.GreetingService;

public class FrenchGreetingService implements GreetingService{

	@Override
	public String greeting() {
		return "Bonjour monde";
	}

}
