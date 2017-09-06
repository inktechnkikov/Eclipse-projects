package vehiclebuilder;

import entities.Car;
import enums.VehicleEnums;
import interfaces.IVehicle;

public class VehicleFactory {

	public VehicleFactory() {

	}

	public static IVehicle createNewVehicle(Class vehicles) {
		if (vehicles.equals(VehicleEnums.CAR)) {
			return new Car("Diesel", 145, 220);
		}
		return null;
	}
}