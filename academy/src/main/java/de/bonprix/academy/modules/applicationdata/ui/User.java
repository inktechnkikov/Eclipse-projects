package de.bonprix.academy.modules.applicationdata.ui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.bonprix.dto.Entity;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@GeneratePojoBuilder(intoPackage = "*.builder")
public class User extends Entity {

	private String firstName;
	private String lastName;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
