package Main;

import Main.Interfaces.iMapCollection;
import Main.Models.HashMapCollection;
import Main.Models.TreeMapCollection;

public class Main {

	public static void main(String[] args) {
		iMapCollection treeMapCollection = new TreeMapCollection();
		treeMapCollection.addKeyValue();
		treeMapCollection.printItems();
		
	}

}
