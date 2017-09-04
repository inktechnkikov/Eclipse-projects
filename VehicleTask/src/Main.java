import entities.Car;
import interfaces.Vehicle;

public class Main {

	public static void main(String[] args) throws Exception {
		
			Vehicle car = new Car("Benzin", 160, 220);
			printCommands(car);
			startCommands(car);
			
	}
	public static void printCommands(Vehicle vehicle) {
		System.out.println(vehicle.showCommands());
	}
	public static void startCommands(Vehicle vehicle) throws Exception {
		vehicle.parseCommands();
	}
	
}
