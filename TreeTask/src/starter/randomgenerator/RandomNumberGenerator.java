package starter.randomgenerator;

import java.util.Random;

import starter.constantvalues.ConstanValues;
import starter.interfaces.ISimpleNode;

public class RandomNumberGenerator implements ISimpleNode {
	
	private int getRandomNumber;

	@Override
	public int getRandomNumber() {
		this.getRandomNumber = new Random().nextInt(ConstanValues.maxValueRange);
		System.out.println(getRandomNumber);
		return this.getRandomNumber;
	}
	

}
