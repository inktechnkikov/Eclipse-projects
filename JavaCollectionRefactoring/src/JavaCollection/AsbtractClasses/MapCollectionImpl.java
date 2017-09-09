package JavaCollection.AsbtractClasses;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import JavaCollection.Interfaces.ICollectionUtils;

public abstract class MapCollectionImpl implements ICollectionUtils {

	private Map<Integer, Integer> engineMap;
	
	public MapCollectionImpl(Map<Integer, Integer> engine) {
		this.engineMap = engine;
	}

	@Override
	public void insterItems(int elements) {
		Random random = new Random(10);
		int i = 0;
		while (i < elements) {
			i++;
			Integer keInteger = random.nextInt();
			Integer valInteger = random.nextInt();
			this.engineMap.put(keInteger, valInteger);
		}

	}

	@Override
	public void searchItem(int searchElement) {
		for (Integer key : this.engineMap.keySet()) {
			this.engineMap.get(key);
		}
	}

	@Override
	public void removeItem(int removeElement) {
		this.engineMap.remove(removeElement);

	}

	@Override
	public void printItems() {
		for (Map.Entry<Integer, Integer> mapCollection : engineMap.entrySet()) {
			System.out.printf("Key %s Value %d%n",mapCollection.getKey(),mapCollection.getValue());
		}

	}

	@Override
	public void addItem(int item) {
		
	}
	
}
