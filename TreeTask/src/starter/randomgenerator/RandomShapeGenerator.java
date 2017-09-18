package starter.randomgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.regexp.internal.recompile;

import starter.constantvalues.ConstanValues;
import starter.interfaces.IShapeNode;

public class RandomShapeGenerator extends RandomColorGenerator implements IShapeNode{
	
	private List<String> shapeList;
	private int getRandomShape;
	
	@Override
	public String getRandomShape() {
		this.shapeList = new ArrayList<String>();
		String[] shapes = {
				"diamond",
				"triangle",
				"circle",
				"rectangle"
		};
		this.shapeList.addAll(Arrays.asList(shapes));
		this.getRandomShape = new Random().nextInt(this.shapeList.size());
		String getShape = this.shapeList.get(getRandomShape);
		System.out.println(getShape);
		return getShape;
	}

	@Override
	public int getRandomNumber() {
		// TODO Auto-generated method stub
		return super.getRandomNumber();
	}

	@Override
	public String getRandomColor() {
		// TODO Auto-generated method stub
		return super.getRandomColor();
	}
	
}
