import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ArrUtil arrUtil = new ArrUtil(5);
		arrUtil.addNumbers();
		arrUtil.printNumbersInCollection();
		arrUtil.getMinNumber();
		System.out.println();
		arrUtil.getMaxNumber();
		System.out.println();
		arrUtil.sumOfAllNumbers();
	}

}
