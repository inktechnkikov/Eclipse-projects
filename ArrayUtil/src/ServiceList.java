import java.io.IOException;

public interface ServiceList {
	void addNumbers() throws IOException;
	void printNumbersInCollection();
	int getMinNumber();
	int getMaxNumber();
	int sumOfAllNumbers();
}
