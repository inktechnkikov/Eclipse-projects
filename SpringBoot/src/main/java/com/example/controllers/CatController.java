package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.interfaces.Animal;

@Controller
public class CatController {

	@Autowired
	private Animal cat;
	
	@GetMapping("/cat")
	@ResponseBody
	private String getCatPage() {
		this.cat.setName("Kitie");
		return this.cat.getName();
		
	}
}
