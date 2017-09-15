package Main;


import Main.Interfaces.IColoredNode;
import Main.Interfaces.IShapeNode;
import Main.Interfaces.ISimpleMode;
import Main.Models.ColoredNode;
import Main.Models.ShapeNode;
import Main.Models.SimpleNode;
import Main.TreeImpl.Tree;

public class Main {

	public static void main(String[] args) {
	
		/* Tree<Integer> tree = new Tree<>(7,
                new Tree<>(19,
                new Tree<>(1),
                new Tree<>(12),
                        new Tree<>(31)),
                new Tree<>(21),
                new Tree<>(14,
                        new Tree<>(23),
                        new Tree<>(6)));
        //  Iterable res = tree.orderDFS();
          StringBuilder builder = new StringBuilder();
      //  System.out.println(builder.append(' ').append(res));
        Iterable printRes = tree.orderBFS();
        System.out.println(builder.append(' ').append(printRes));
        */
	//	IShape shape = new Shape();
		//shape.addRandomNumberAdnRandomShape();
			ShapeNode shapeNode = new ShapeNode();
			shapeNode.addRandomItems();
	}

}
