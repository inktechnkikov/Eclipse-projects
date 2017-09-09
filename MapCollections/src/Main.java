import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		Map<String, Double> studentMap = new TreeMap<>();
		studentMap.put("Djons", 6.00);
		studentMap.put("Stamat", 4.55);
		studentMap.put("Franklin", 5.50);
		studentMap.put("Manteko", 5.23);
		studentMap.put("Unufri", 4.00);
		studentMap.put("Momo", 5.63);
		studentMap.put("Wiliam", 3.45);
		studentMap.put("Stamat", 4.00);
		studentMap.put("Robin", 5.23);
		//studentMap.remove("Djons");
		
		for(Map.Entry<String, Double> studentEntry:studentMap.entrySet()) {
			System.out.printf("Student %s has %.2f%n",studentEntry.getKey(),studentEntry.getValue());
		}
		
	}

}
