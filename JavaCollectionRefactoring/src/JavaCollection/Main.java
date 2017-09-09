package JavaCollection;

import java.lang.reflect.Method;

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
		
	}
}
