package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.interfaces.Animal;

@Controller
public class DogController {
	
	@Autowired
	private Animal dog;
	
	@GetMapping("/dogname")
	@ResponseBody
	public String getDogPage() {
		dog.setName("Ricko");
		return dog.getName();
	}
}
