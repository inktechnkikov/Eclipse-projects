package starter;

import java.util.ArrayList;
import java.util.List;

import interfaces.AvailableOperations;
import interfaces.ExecutableOperations;
import interfaces.IMenu;
import interfaces.IVehicle;
import menu.MenuItem;
import vehiclebuilder.VehicleFactory;

public class StarterManager {
	private Class[] availableVehicles;
	private IMenu menu;

	public StarterManager(Class... availableVehiclesUnits) {
		this.availableVehicles = availableVehiclesUnits;
	}

	public void setMenu(IMenu menuSelector) {
		this.menu = menuSelector;
	}

	private Class selectVehicle() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		for (Class vehicClass : availableVehicles) {
			menuItems.add(new MenuItem(-1, vehicClass.toString()));
		}
		menu.setMenuItems(menuItems);
		MenuItem selectedItem = menu.requestSelection();
		return availableVehicles[menuItems.indexOf(selectedItem)];
	}

	public ExecutableOperations executeCurrent(IVehicle iVehicle) {
		List<MenuItem> menuItems = new ArrayList<>();
		List<ExecutableOperations> operations = iVehicle.executableOperations();
		for (ExecutableOperations executableOperations : operations) {
			menuItems.add(new MenuItem(-1, executableOperations.getName()));
		}
		menu.setMenuItems(menuItems);
		MenuItem selectedItem = menu.requestSelection();
		return operations.get(menuItems.indexOf(selectedItem));
	}

	public void start() {
		Class selectVehicle = selectVehicle();
		IVehicle vehicle = VehicleFactory.createNewVehicle(selectVehicle);
	}
}
