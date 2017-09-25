package starter.randomgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.NEW;

import starter.models.ColoredNode;
import starter.models.Node;
import starter.models.ShapeNode;
import starter.models.SimpleNode;

public class RandomNodeGenerator{
	

	public static SimpleNode getSimpleNode() {
		return new SimpleNode();
	}
	public static ColoredNode getColoredNode() {
		return new ColoredNode();
	}
	public static ShapeNode getShapeNode() {
		return new ShapeNode();
	}
	public static Node generate() {
		return new Node[] {
				getSimpleNode(),
				getColoredNode(),
				getShapeNode()
		}
		new Random().nextInt(3);
	}
}
