package com.dependencyinjection.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.dependencyinjection.interfaces.GreetingService;
import com.dependencyinjection.serviceimplementation.EnglishGreetingService;
import com.dependencyinjection.serviceimplementation.FrenchGreetingService;

@Configuration
public class GreetingServiceConfigoration {
	
	@Bean
	public GreetingService englishGreetingService() {
		return new EnglishGreetingService();
	}
	@Bean
	//@ConditionalOnProperty(name = "language.name",havingValue = "french")
	public GreetingService frencGreetingService() {
		return new FrenchGreetingService();
	}
}
