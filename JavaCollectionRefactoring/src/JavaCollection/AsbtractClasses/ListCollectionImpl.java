package JavaCollection.AsbtractClasses;

import java.util.Iterator;
import java.util.List;

import JavaCollection.Interfaces.ICollectionUtils;

public abstract class ListCollectionImpl implements ICollectionUtils{

	
	private List<Integer> listEngine;
	
	public ListCollectionImpl(List<Integer> engine) {
		this.listEngine = engine;
	}
	@Override
	public void insterItems(int elements) {
		for (int i = 0; i < elements; i++) {
			this.listEngine.add(i);
		}
		
	}

	@Override
	public void searchItem(int searchElement) {
		Iterator<Integer> iterator = listEngine.iterator();
		while(iterator.hasNext()) {
			if(this.listEngine.contains(searchElement)) {
				System.out.println(searchElement);
			}
		}
		
	}

	@Override
	public void removeItem(int removeElement) {
		this.listEngine.remove(removeElement);
		
	}

	@Override
	public void printItems() {
		Iterator<Integer> iterator = listEngine.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
	@Override
	public void addItem(int item) {
		this.listEngine.add(item);
		
	}
	
	
}
