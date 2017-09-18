package starter.randomgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import starter.interfaces.IColoredNode;

public class RandomColorGenerator extends RandomNumberGenerator implements IColoredNode{
	
	private List<String> colorList;
	private int getRandomShape;
	
	@Override
	public int getRandomNumber() {
		return super.getRandomNumber();
	}


	@Override
	public String getRandomColor() {
		this.colorList = new ArrayList<>();
		String[] colorStrings = {
				"red",
				"green",
				"blue",
				"yellow",
				"white"
		};
		for (int i = 0; i < colorStrings.length; i++) {
			this.colorList.addAll(Arrays.asList(colorStrings));
		}
		this.getRandomShape = new Random().nextInt(this.colorList.size());
		String getShape = this.colorList.get(getRandomShape);
		System.out.println(getShape);
		return getShape;
	}

}
