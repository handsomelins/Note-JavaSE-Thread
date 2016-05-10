package cn.saicent.demo;

import cn.saicent.utils.core.ThreadUtil;

public class StopThreadDemo {
	
	/**
	 * >> 测试{thread.interrupt();}方法。
	 * 
	 * 调用thread.interrupt();方法，并不能让线程立刻结束。
	 * 它的作用仅仅是设置中断标记而已，具体停不停止要看正在运行的线程。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread1() throws Exception {
		
		MyThread1 thread = new MyThread1("thread1");
		thread.start();
		
		Thread.sleep(2000);
		
		System.out.println("main thread will stop child thread.");
		thread.interrupt();
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 测试{thread.interrupted();}方法。
	 * 
	 * 两次调用thread.interrupted();方法都返回false，表示线程并未被中断。
	 * 因为interrupted();方法是属于Thread类的，他实际会执行Thread.interrupted();方法返回当前线程的中断状态。
	 * 而当前线程是main，所以返回false。
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static void testStopThread2() throws Exception {
		
		MyThread1 thread = new MyThread1("thread1");
		thread.start();
		
		Thread.sleep(1000);
		thread.interrupt();
		
		System.out.println("是否停止1？= " + thread.interrupted());
		System.out.println("是否停止2？= " + thread.interrupted());
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 测试{Thread.interrupted();}方法。
	 * 
	 * 第一次调用Thread.interrupted();方法，返回true；第二次调用Thread.interrupted();方法返回false。
	 * 原因是thread.interrupt();方法可以设置中断状态，而Thread.interrupted();方法具有清除中断状态的作用。
	 * 所以，第一次调用Thread.interrupted();方法，返回true，与此同时，也清除了中断状态。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread3() throws Exception {
		
		Thread.currentThread().interrupt();
		System.out.println("是否停止1？= " + Thread.interrupted());
		System.out.println("是否停止2？= " + Thread.interrupted());
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 测试{thread.isInterrupted();}方法。
	 * 
	 * 两次调用thread.isInterrupted();方法都返回true。
	 * 
	 * 说明两点问题：
	 *  1.该方法是实例方法，返回的正式调用这个方法的thread对象的中断状态。
	 *  2.该方法不能清除中断状态。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread4() throws Exception {
		
		MyThread1 thread = new MyThread1("thread1");
		thread.start();
		
		Thread.sleep(1000);
		thread.interrupt();
		
		System.out.println("是否停止1？= " + thread.isInterrupted());
		System.out.println("是否停止2？= " + thread.isInterrupted());
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 通过break来跳出for循环。
	 * 
	 * 采用这种机制确实可以“中断”子线程，但是for循环后面如果有代码，还是会继续执行。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread5() throws Exception {
		
		MyThread2 thread = new MyThread2("thread2");
		thread.start();
		
		Thread.sleep(2000);
		thread.interrupt();
		
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 通过return方法来中断线程。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread6() throws Exception {
		
		MyThread3 thread = new MyThread3("thread3");
		thread.start();
		
		Thread.sleep(2000);
		thread.interrupt();
		
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 采用抛异常来中断线程。
	 * 
	 * 采用这种机制可以中断子线程，但并不完美。
	 * 
	 * @throws Exception
	 */
	public static void testStopThread7() throws Exception {
		
		MyThread4 thread = new MyThread4("thread4");
		thread.start();
		
		Thread.sleep(2000);
		thread.interrupt();
		
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 当线程在sleep的时候，被中断。
	 * 
	 *  1.将会抛出异常
	 *  2.清除中断状态
	 * 
	 * @throws Exception
	 */
	public static void testStopThread8() throws Exception {
		
		MyThread5 thread = new MyThread5("thread5");
		thread.start();
		
		Thread.sleep(200);
		thread.interrupt();
		
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 当线程被中断时，进入sleep状态。
	 * 	
	 *  1.将会抛出异常
	 *  2.清除中断状态
	 *  
	 * @throws Exception
	 */
	public static void testStopThread9() throws Exception {
		
		MyThread6 thread = new MyThread6("thread6");
		thread.start();
		
		thread.interrupt();
		System.out.println("main thread is end.");
	}
	
	
	/**
	 * >> 通过stop();方法粗暴中断子线程。
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void testStopThread10() throws Exception {
		
		MyThread7 thread = new MyThread7("thread7");
		thread.start();
		
		Thread.sleep(8000);
		thread.stop();
		
	}
	
	
	/**
	 * >> 通过stop();方法中断线程的不良后果。
	 * 
	 *  1.通过stop();方法中断线程，可能会让一些清理工作得不到完成。
	 *  2.对锁定的对象进行了“解锁”，导致数据得不到同步处理，出现数据不一致的问题。
	 *  
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static void testStopThread11() throws Exception {
		
		SynchronizedObject object = new SynchronizedObject();
		MyThread8 thread = new MyThread8(object);
		
		thread.start();
		Thread.sleep(500);
		thread.stop();
		
		System.out.println(object.getUsername() + ", " + object.getPassword());
	}
	
	
	public static void main(String[] args) throws Exception {
		
		testStopThread8();
	}
}

class MyThread1 extends Thread {
	
	public MyThread1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		ThreadUtil.sayThreadStart();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i = " + (i+1));
		}
		ThreadUtil.sayThreadStop();
	}
}

class MyThread2 extends Thread {
	
	public MyThread2(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		ThreadUtil.sayThreadStart();
		for (int i = 0; i < 500000; i++) {
			
			if (this.isInterrupted()) {
				System.out.println("child thread is end.");
				break;
			}
			
			System.out.println("i = " + (i+1));
		}
		ThreadUtil.sayThreadStop();
	}
}

class MyThread3 extends Thread {
	
	public MyThread3(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < 500000; i++) {
			
			if (this.isInterrupted()) {
				System.out.println("child thread is end.");
				return;
			}
			
			System.out.println("i = " + (i+1));
		}
	}
}

class MyThread4 extends Thread {
	
	public MyThread4(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		try {
			
			for (int i = 0; i < 500000; i++) {
				
				if (this.isInterrupted()) {
					System.out.println("child thread is end.");
					throw new InterruptedException();
				}
				
				System.out.println("i = " + (i+1));
			}
			
		} catch (InterruptedException e) {
			
			System.out.println("child thread throw a exception.");
			e.printStackTrace();
		}
	}
}


class MyThread5 extends Thread {
	
	public MyThread5(String name) {
		super(name);
	}
	
	@Override
	public void run() {
	
		try {
			
			System.out.println("child thread is begin.");
			Thread.sleep(200000);
			System.out.println("child thread is end.");
			
		} catch (InterruptedException e) {
			
			System.out.println("child thread throw a exception.");
			
			if (this.isInterrupted()) {
				System.out.println("child thread is interrrupted.");
			} else {
				System.out.println("child thread is not interrrupted.");
			}
			
			e.printStackTrace();
		}
	}
}

class MyThread6 extends Thread {
	
	public MyThread6(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		try {
			
			System.out.println("child thread is begin.");
			for (int i = 0; i < 500000; i++) {
				System.out.println("i = " + (i + 1));
			}
			
			if (this.isInterrupted()) {
				System.out.println("child thread is interrrupted.");
			} else {
				System.out.println("child thread is not interrrupted.");
			}
			
			System.out.println("child thread is continue.");
			Thread.sleep(10);
			System.out.println("child thread is end.");
			
		} catch (InterruptedException e) {
			
			System.out.println("child thread throw a exception.");
			
			if (this.isInterrupted()) {
				System.out.println("child thread is interrrupted.");
			} else {
				System.out.println("child thread is not interrrupted.");
			}
		}
	}
}

class MyThread7 extends Thread {
	
	private int i = 0;
	
	public MyThread7(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		try {
			
			while (true) {
				i++;
				System.out.println("i = " + i);
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThread8 extends Thread {
	
	private SynchronizedObject object;
	
	public MyThread8(SynchronizedObject object) {
		this.object = object;
	}
	
	public MyThread8(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		
		object.login("b", "bb");
	}
}


class SynchronizedObject {
	
	private String username = "a";
	
	private String password = "aa";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public synchronized void login(String username, String password) {
		
		try {
			
			this.username = username;
			Thread.sleep(100000);
			this.password = password;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

