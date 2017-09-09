package Main.Models;

import java.util.Map;
import java.util.Map.Entry;

import Main.Interfaces.iMapCollection;

public abstract class MapColImp implements iMapCollection {

	private Map<String, Integer> mapCollectEngine;
	
	 public MapColImp(Map<String, Integer> engine) {
		this.mapCollectEngine = engine;
	}
	
	@Override
	public void addKeyValue() {
		this.mapCollectEngine.put("Stamat", 1);
		this.mapCollectEngine.put("Adams", 2);
		this.mapCollectEngine.put("Robert", 45);
		this.mapCollectEngine.put("Ava", 88);
		this.mapCollectEngine.put("Igor", 78);
		
	}

	@Override
	public void printItems() {
		for(Entry<String, Integer> collectionEntry:mapCollectEngine.entrySet()) {
			System.out.printf("Name - %s number - %d%n",collectionEntry.getKey(),collectionEntry.getValue());
		}
	}

}
