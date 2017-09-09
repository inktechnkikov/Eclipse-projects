package JavaCollection;

import JavaCollection.Constants.Elements;
import JavaCollection.Interfaces.ICollectionUtils;
import JavaCollection.Models.Map.TreeMapCollection;

public class Main {

	public static void main(String[] args) {
		ICollectionUtils treeMap = new TreeMapCollection();
		treeMap.insterItems(Elements.ELEMENTSTOADD);
		treeMap.printItems();
	}

}
