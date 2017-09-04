package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(reader.readLine());
		int sum = 0;
		for (int i = 0; i < a; i++) {
			int num = Integer.parseInt(reader.readLine());
			sum+=num;
		}
		System.out.println(sum);
	}

}
