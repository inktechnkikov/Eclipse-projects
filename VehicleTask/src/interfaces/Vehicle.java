package interfaces;

public interface Vehicle {
	
	String getEngineType();
	Integer hoursePower();
	Integer getMaxSpeed();
	StringBuilder showCommands();
	void parseCommands() throws Exception;
}
