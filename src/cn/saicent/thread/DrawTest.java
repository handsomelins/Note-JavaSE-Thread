package cn.saicent.thread;

import java.util.concurrent.locks.ReentrantLock;

public class DrawTest {

	public static void main(String[] args) throws InterruptedException {
		
//		Account account = new Account("123", 0);
//		new DespositThread("+ 存款者甲", account, 800).start();
//		new DrawThread("- 取钱者", account, 800).start();
//		
		ReentrantLock reentrantLock = new ReentrantLock();
		
		reentrantLock.lock();
		
		reentrantLock.wait();
		
		reentrantLock.unlock();
		
	}

}
