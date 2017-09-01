import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArrUtil implements ServiceList{
	private int size;
	private ArrayList<Integer> data = new ArrayList<Integer>();
	public ArrUtil(int size) {
		this.size = size;
	}
	
	@Override
	public void addNumbers() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < this.size; i++) {
			int numToAdd = Integer.parseInt(reader.readLine());
			this.data.add(numToAdd);
		}
	}

	@Override
	public void printNumbersInCollection() {
		for (Integer num : data) {
			System.out.println(num);
		}
	}

	@Override
	public int getMinNumber() {
		int min = this.data.get(0);
		for (int i = 0; i < this.data.size(); i++) {
			if(this.data.get(i) < min) {
				min = this.data.get(i);
			}
		}
		System.out.printf("The min number is %s",min);
		return min;
	}

	@Override
	public int getMaxNumber() {
		int max = this.data.get(0);
		for (int i = 0; i < this.data.size(); i++) {
			if(this.data.get(i) > max) {
				max = this.data.get(i);
			}
		}
		System.out.printf("The max number is %s",max);
		return max;
	}

	@Override
	public int sumOfAllNumbers() {
		int sum = 0;
		for (int i = 0; i < this.data.size(); i++) {
			sum += this.data.get(i);
		}
		System.out.printf("Total sum of all numbers %s",sum);
		return sum;
	}
		
	
}
