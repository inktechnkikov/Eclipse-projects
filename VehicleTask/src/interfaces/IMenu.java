package interfaces;

import java.util.List;

import menu.MenuItem;

public interface IMenu {
	void setMenuItems(List<MenuItem> items);
	MenuItem requestSelection();
}
