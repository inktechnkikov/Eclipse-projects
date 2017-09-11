package Main.AbstractClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.regexp.internal.recompile;

import Main.Interfaces.IShape;
import Main.NumberConstants.ConstantValues;

public class Shape implements IShape{

	private Random randomGenerator;
	private List<String> shapeList;
	private List<Integer> numberList;
	@Override
	public String addRandomShape() {
		this.shapeList = new ArrayList<>();
		String[] shapes = {
				"rectangle",
				"triangle",
				"diamond",
				"circle",
		};
		for (int i = 0; i < shapes.length; i++) {
			this.shapeList.add(shapes[i]);
		}
		this.randomGenerator = new Random();
		int index = randomGenerator.nextInt(this.shapeList.size());
		String getRandomshape = this.shapeList.get(index);
		System.out.print(getRandomshape);
		return getRandomshape;
		
	}
	@Override
	public int addRandomNumber() {
		this.numberList = new ArrayList<>();
		int[] numbers = {
				1,2,3,4,5,6,7,8,9,10
		};
		for (int i = 0; i < numbers.length; i++) {
			this.numberList.add(numbers[i]);
		}
		this.randomGenerator = new Random();
		int numIndex = randomGenerator.nextInt(this.numberList.size());
		int getRandomNumber = this.numberList.get(numIndex);
		System.out.println(getRandomNumber);
		return getRandomNumber;
	}
	@Override
	public void addRandomNumberAdnRandomShape() {
		addRandomNumber();
		addRandomShape();	
	}
	
	
	
}
