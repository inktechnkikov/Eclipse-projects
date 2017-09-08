package Main.Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Main.Interfaces.MapCollection;

public class HashMapCollection implements MapCollection {

	private HashMap<Integer, Integer> data = new HashMap<>();

	public HashMapCollection() {

	}

	@Override
	public void addKeysAndValues(Integer size) {
		Random random = new Random(50);
		Integer i = 0;
		while (i < size) {
			i++;
			Integer key = random.nextInt(50);
			Integer value = random.nextInt(50);
			Integer imap = this.data.put(key, value);
		}

	}

	@Override
	public void removeByKeyAndValue(Integer key, Integer value) {

	}

	@Override
	public void searchByKey(Integer key) {
		for (Integer keys : this.data.values()) {
			if (this.data.containsKey(key)) {
				System.out.println("Key - > " + key);
			} else {
				System.out.println("No such key");
			}
		}

	}

	@Override
	public void searchByValue(Integer value) {
		for (Integer value1 : this.data.values()) {
			if (this.data.containsValue(value)) {
				System.out.println("Value - > " + value);
			} else {
				System.out.println("No such value");
			}
		}

	}

	@Override
	public void printElements() {
		for (Map.Entry dEntry : data.entrySet()) {
			// System.out.println(dEntry.getKey()+ " " + dEntry.getValue());
			System.out.printf("inserting key -> %s inserting value -> %s\n", dEntry.getKey(), dEntry.getValue());
		}

	}

}
