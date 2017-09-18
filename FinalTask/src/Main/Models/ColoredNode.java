package Main.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Main.Interfaces.IColoredNode;
import Main.NumberConstants.ConstantValues;

public class ColoredNode extends SimpleNode implements IColoredNode{

	private List<String> colorList;
	private Random randomGenerator;
	
	@Override
	public String addRandomColor() {
		this.colorList = new ArrayList<String>();
		String[] colors = {
				"red",
				"green",
				"blue",
				"yellow",
				"white"
		};
		for (int i = 0; i < colors.length; i++) {
			this.colorList.add(colors[i]);
		}
		
		this.randomGenerator = new Random();
		int getIndex = randomGenerator.nextInt(this.colorList.size());
		String getRandomShape = this.colorList.get(getIndex);
		System.out.println(getRandomShape);
		return getRandomShape;
 	}
	
	@Override
	public int addRandomNumber() {
		
		return super.addRandomNumber();
	}
	
	
}
