package JavaCollection.Interfaces;

import JavaCollection.Constants.Elements;

public interface ICollectionUtils extends Elements{
	
	void insterItems(int elements);
	
	void addItem(int item);
	
	void searchItem(int searchElement);
	
	void removeItem(int removeElement);
	
	void printItems();
}
