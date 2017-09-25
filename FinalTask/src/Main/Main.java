package Main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;

import Main.Interfaces.IColoredNode;
import Main.Interfaces.IShapeNode;
import Main.Interfaces.ISimpleMode;
import Main.Models.ColoredNode;
import Main.Models.SimpleNode;
import Main.TreeImpl.Node;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int x  = Integer.parseInt(reader.readLine());
		int y = Integer.parseInt(reader.readLine());
		
	/*	Node<Integer> Node = new Node<>(7,
                new Node<>(19,
                new Node<>(1),
                new Node<>(12),
                        new Node<>(31)),
                new Node<>(21),
                new Node<>(14,
                        new Node<>(23),
                        new Node<>(6)));
		
        //  Iterable res = Node.orderDFS();
          StringBuilder builder = new StringBuilder();
      //  System.out.println(builder.append(' ').append(res));
        Iterable printRes = Node.orderBFS();
        System.out.println(builder.append(' ').append(printRes));
        */
	//	IShape shape = new Shape();
		//shape.addRandomNumberAdnRandomShape();
			
	}

}
