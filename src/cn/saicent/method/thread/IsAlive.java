package cn.saicent.method.thread;

public class IsAlive extends Thread {
	
	public IsAlive() {
		
		System.out.println("ThreadMethodIsAlive---begin");
		System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
		System.out.println("this.getName() = " + this.getName());
		System.out.println("this.isAlive() = " + this.isAlive());
		System.out.println("ThreadMethodIsAlive---end");
		System.out.println();
	}
	
	@Override
	public void run() {
		
		System.out.println("run---begin");
		System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
		System.out.println("this.getName() = " + this.getName());
		System.out.println("this.isAlive() = " + this.isAlive());
		System.out.println("run---end");
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		IsAlive threadMethodIsAlive = new IsAlive();
		Thread thread = new Thread(threadMethodIsAlive);
		
		System.out.println(">>>main begin, child thread isAlive = " + thread.isAlive());
		
		thread.setName("A");
		thread.start();
		
		System.out.println(">>>main end, child thread isAlive = " + thread.isAlive());
		
	}
}
