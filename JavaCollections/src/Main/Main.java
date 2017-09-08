package Main;

import java.util.HashMap;
import java.util.Random;

import Main.Interfaces.MapCollection;
import Main.Models.HashMapCollection;

public class Main {

	public static void main(String[] args) {
	MapCollection hasMapCollection = new HashMapCollection();
	hasMapCollection.addKeysAndValues(4);
	hasMapCollection.printElements();
	hasMapCollection.searchByKey(43);
	}

}
