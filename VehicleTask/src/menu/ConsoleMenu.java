package menu;

import java.util.List;
import java.util.Scanner;

import interfaces.IMenu;

public class ConsoleMenu implements IMenu{
	private List<MenuItem> items;

	@Override
	public void setMenuItems(List<MenuItem> items) {
		this.items = items;
		
	}

	@Override
	public MenuItem requestSelection() {
		for(int i = 0;i < items.size();i++) {
			System.out.println(i + "(" + items.get(i).getName());
		}
		if(!items.isEmpty()) {
			Scanner scanner = new Scanner(System.in);
			return this.items.get(scanner.nextInt());
		}
		return null;
	}
	
}
