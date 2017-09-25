package com.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dependencyinjection.interfaces.GreetingService;
import com.dependencyinjection.serviceimplementation.EnglishGreetingService;

@SpringBootApplication
public class DependencyInjectionSpringStyleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionSpringStyleApplication.class, args);
	}
}
