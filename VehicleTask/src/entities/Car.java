package entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import interfaces.Vehicle;

public class Car implements Vehicle {
	String engine;
	Integer hoursePower;
	Integer maxSpeed;
	StringBuilder builder = new StringBuilder();

	public Car(String engine, Integer hoursePower, Integer maxSpeed) {
		this.engine = engine;
		this.hoursePower = hoursePower;
		this.maxSpeed = maxSpeed;
	}

	@Override
	public String getEngineType() {

		return "Engine is " + this.engine;
	}

	@Override
	public Integer hoursePower() {
		return this.hoursePower;
	}

	@Override
	public Integer getMaxSpeed() {
		return this.maxSpeed;
	}

	@Override
	public StringBuilder showCommands() {
		this.builder = new StringBuilder();
		return builder.append("Print engine type").append("\n").append("print Hourse power").append("\n")
				.append("print Max Speed");
	}

	@Override
	public void parseCommands() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputCommand=reader.readLine();
		if(inputCommand.equals("Print engine type")){
			System.out.println(this.getEngineType());
		}else if(inputCommand.equals("print Horse power")) {
			System.out.println(this.hoursePower);
		}else if(inputCommand.equals("print Max Speed")) {
			System.out.println(this.getMaxSpeed());
		}
	}


	
	
}
