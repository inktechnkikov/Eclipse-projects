import entities.Car;
import menu.ConsoleMenu;
import starter.StarterManager;

public class Main {

	public static void main(String[] args) {
		StarterManager manager = new StarterManager(Car.class);
		manager.setMenu(new ConsoleMenu());
		manager.start();
	}

}
