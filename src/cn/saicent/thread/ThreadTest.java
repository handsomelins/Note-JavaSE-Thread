package cn.saicent.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest extends Thread{

	private static ReentrantLock reentrantLock = new ReentrantLock();
	private int i;
	
	public void run() {
		
		reentrantLock.lock();
		
		for(; i < 1000; i++) {
			
			System.out.println(getName() + " " + i);
			
			if(i == 20) {
				
				Thread.yield();
			}
		}
		
		reentrantLock.unlock();
		
	}
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadTest test1 = new ThreadTest();
		
		test1.setPriority(Thread.MAX_PRIORITY);
		test1.start();
		
		
		ThreadTest test2 = new ThreadTest();
		test2.setPriority(Thread.MIN_PRIORITY);
		test2.start();
		
		
	}
	

}
