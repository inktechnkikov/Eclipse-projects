package starter;

import starter.interfaces.IShapeNode;
import starter.interfaces.ISimpleNode;
import starter.models.ColoredNode;
import starter.models.Node;
import starter.models.ShapeNode;
import starter.models.SimpleNode;
import starter.nodefactory.NodeFacotry;
import starter.randomgenerator.RandomNodeGenerator;
import starter.treeimpl.TreeGenerator;

public class Main {

	public static void main(String[] args) {
		NodeFacotry nodeFacotry = new NodeFacotry();
		nodeFacotry.getRandomNodeGenerator().generate();
		System.out.println(nodeFacotry.toString());
	}

}
