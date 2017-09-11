package FiveThreads;import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Runnable task = () ->{
			for(int i = 0;i<10;i++) {
				System.out.print(i + " ");
			}
		};
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(task);
			threads[i].start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
	}

}
