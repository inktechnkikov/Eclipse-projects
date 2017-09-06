package interfaces;

public interface IVehicle extends AvailableOperations{
	void engineStart();
	String getEngineType();
	Integer hoursePower();
	Integer getMaxSpeed();
	void gearUp();
	void gearDown();
}
