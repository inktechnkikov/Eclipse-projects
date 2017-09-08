package Main.Interfaces;

public interface MapCollection {
	
	void addKeysAndValues(Integer size);
	
	void removeByKeyAndValue(Integer key,Integer value);
	
	void searchByValue(Integer value);
	
	void searchByKey(Integer key);
	
	void printElements();
}
