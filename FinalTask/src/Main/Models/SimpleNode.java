package Main.Models;

import java.util.Random;

import Main.Interfaces.ISimpleMode;
import Main.NumberConstants.ConstantValues;

public class SimpleNode implements ISimpleMode{
	
	private int randomNumber;

	@Override
	public int addRandomNumber() {
		this.randomNumber = new Random().nextInt(ConstantValues.maxRangeOfNumbers);
		System.out.println(randomNumber);
		return this.randomNumber;
	}
	public void addRandomItems() {
		addRandomNumber();
	}

	
}
