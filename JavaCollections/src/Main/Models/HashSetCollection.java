package Main.Models;

import java.util.HashSet;
import java.util.Set;

import Main.Interfaces.iSetCollection;

public class HashSetCollection implements iSetCollection{

	private Set<Integer> data = new HashSet<>();
	
	public HashSetCollection() {
		
	}

	@Override
	public void instertElements(int elements) {
		for(int i = 0;i < elements;i++) {
			this.data.add(i);
		}
		
	}

	@Override
	public void searchElements(int searchElement) {
		if(this.data.contains(searchElement)) {
			System.out.println(searchElement);
		}
		
	}

	@Override
	public void removeElement(int removeElement) {
		this.data.remove(removeElement);
	}

	@Override
	public void printElements() {
		for (Integer element : data) {
			System.out.println(element);
		}
		
	}
	
	
}
