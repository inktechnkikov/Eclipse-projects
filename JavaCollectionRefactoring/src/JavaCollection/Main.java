package JavaCollection;

import JavaCollection.Constants.Elements;
import JavaCollection.Interfaces.ICollectionUtils;
import JavaCollection.Models.List.ListCollection;
import JavaCollection.Models.Map.HashMapCollection;
import JavaCollection.Models.Map.TreeMapCollection;
import JavaCollection.Models.Sets.HashSetCollection;

public class Main {

	public static void main(String[] args) {
		ICollectionUtils mapCollection = new HashMapCollection();
		mapCollection.insterItems(Elements.ELEMENTSTOADD);
		mapCollection.printItems();
		mapCollection.addItem(1);
		mapCollection.printItems();
	}

}
