package com.example.entities;
import com.example.interfaces.Animal;

public class Dog implements Animal{
	private String name;
	public Dog() {
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
