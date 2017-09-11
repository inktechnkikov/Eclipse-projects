
public class Main {

	public static void main(String[] args) {
		
		Runnable task = () ->{
			for (int i = 0; i < 10; i++) {
				System.out.printf("[%s] ",i);
			}
		};
		Thread thread = new Thread(task);
		thread.start();
	}

}
