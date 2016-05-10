package cn.saicent.method.thread;

public class Sleep extends Thread {
	
	@Override
	public void run() {
	
		try {
			
			System.out.println("run threadName = " + currentThread().getName() + " begin");
			
			Thread.sleep(2000);
			
			System.out.println("run threadName = " + currentThread().getName() + " end");
			
		} catch (InterruptedException e) {
			
		}
	}
	
	public static void main(String[] args) {
		
		Sleep threadMethodSleep = new Sleep();
		
		System.out.println("begin = " + System.currentTimeMillis());
		
		/**
		 * begin = 1461726271835
		 * run threadName = main begin
		 * run threadName = main end
		 * end = 1461726273837
		 */
		threadMethodSleep.run();
		
		/**
		 * begin = 1461726224327
		 * end = 1461726224327
		 * run threadName = Thread-0 begin
		 * run threadName = Thread-0 
		 */
		//threadMethodSleep.start();
		
		System.out.println("end = " + System.currentTimeMillis());
		
	}
}
