package com.example.entities;

import com.example.interfaces.Animal;

public class Cat implements Animal{
	private String name;
	
	public Cat() {
	}

	@Override
	public String getName() {
	return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	
}
