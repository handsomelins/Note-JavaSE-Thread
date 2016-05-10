package cn.saicent.thread;

public class RunableTest implements Runnable {

	public RunableTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		for(int i = 0; i < 100; i++) {
			
			System.out.println(Thread.currentThread().getName());
			System.out.println(i);
		}

	}
	
	public static void main(String[] args) {
		
		new Thread(new RunableTest(), "大傻逼").start();
		
	}

}
