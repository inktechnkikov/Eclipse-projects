package JavaCollection.AsbtractClasses;

import java.util.Set;

import JavaCollection.Interfaces.ICollectionUtils;

public abstract class SetCollectionImpl implements ICollectionUtils{

	private Set<Integer> setCollectionEngine;
	
	public SetCollectionImpl(Set<Integer> engine) {
		this.setCollectionEngine = engine;
	}

	@Override
	public void insterItems(int elements) {
		for (int i = 0; i < elements; i++) {
			this.setCollectionEngine.add(i);
		}
		
	}

	@Override
	public void searchItem(int searchElement) {
		if(this.setCollectionEngine.contains(searchElement)) {
			System.out.println(searchElement);
		}
		
	}

	@Override
	public void removeItem(int removeElement) {
		this.setCollectionEngine.remove(removeElement);
		
	}

	@Override
	public void printItems() {
		for (Integer item : setCollectionEngine) {
			System.out.println(item);
		}
		
	}
		
	
}
