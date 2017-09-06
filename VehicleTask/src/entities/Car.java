package entities;

import java.util.ArrayList;
import java.util.List;

import interfaces.ExecutableOperations;
import interfaces.IVehicle;

public class Car implements IVehicle {
	String engine;
	Integer hoursePower;
	Integer maxSpeed;
	
	public Car(String engine, Integer hoursePower, Integer maxSpeed) {
		this.engine = engine;
		this.hoursePower = hoursePower;
		this.maxSpeed = maxSpeed;
	}

	@Override
	public void engineStart() {
		
		System.out.println("Car engine is starting...");
		
	}

	@Override
	public String getEngineType() {
		return this.engine;
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
	public void gearUp() {
		System.out.println("Shift gear up");
		
	}

	@Override
	public void gearDown() {
		System.out.println("Shift gear down");
		
	}

	@Override
	public List<ExecutableOperations> executableOperations() {
		List<ExecutableOperations> operations = new ArrayList<ExecutableOperations>();
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
			return "start engine";
			}
			
			@Override
			public void executeOperation() {
				engineStart();
				
			}
		});
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
				return "get type of engine";
			}
			
			@Override
			public void executeOperation() {
				getEngineType();
				
			}
		});
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
				return "get hourse power";
			}
			
			@Override
			public void executeOperation() {
				hoursePower();
				
			}
		});
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
				return "get max speed";
			}
			
			@Override
			public void executeOperation() {
				getMaxSpeed();
				
			}
		});
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
				return "gear up";
			}
			
			@Override
			public void executeOperation() {
				gearUp();
			}
		});
		operations.add(new ExecutableOperations() {
			
			@Override
			public String getName() {
			return "gear down";
			}
			
			@Override
			public void executeOperation() {
				gearDown();
				
			}
		});
		return operations;
	}
}
