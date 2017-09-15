package Main.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Main.Interfaces.IShapeNode;
import sun.net.www.content.text.plain;

public class ShapeNode extends ColoredNode implements IShapeNode{

	private List<String> shapeList;;
	private Random randomGenerator;
	@Override
	public String getRandomShape() {
		this.shapeList = new ArrayList<String>();
		String[] shapes = {
				"diamond",
				"triangle",
				"circle",
				"rectangle"
		};
		for (int i = 0; i < shapes.length; i++) {
			this.shapeList.add(shapes[i]);
		}
		
		this.randomGenerator = new Random();
		int getIndex = randomGenerator.nextInt(this.shapeList.size());
		String getRandomShape = this.shapeList.get(getIndex);
		System.out.println(getRandomShape);
		return getRandomShape;
	}

	@Override
	public String addRandomColor() {
		// TODO Auto-generated method stub
		return super.addRandomColor();
	}

	@Override
	public int addRandomNumber() {
		// TODO Auto-generated method stub
		return super.addRandomNumber();
	}

	@Override
	public void addRandomItems() {
		super.addRandomNumber();
		addRandomColor();
		getRandomShape();
	}

	
}
	
	

