package cn.saicent.demo;

public class SuspendThreadDemo {
	
	/**
	 * >> 简单的suspend();方法和resume();方法调用演示。
	 * 
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void testSuspendThread1() throws InterruptedException {
		
		SuspendThread1 thread = new SuspendThread1();
		
		System.out.println("child thread is start.");
		thread.start();
		
		// A段
		System.out.println("main thread sleep 5s.");
		Thread.sleep(5000);
		
		System.out.println("child thread is suspend.");
		thread.suspend();
		
		System.out.println("Current time = " + System.currentTimeMillis() + ", i = " + thread.getI());
		
		System.out.println("main thread sleep 5s.");
		Thread.sleep(5000);
		System.out.println("Current time = " + System.currentTimeMillis() + ", i = " + thread.getI());
		
		
		System.out.println(">>>>>>------------------------------------<<<<<<");
		
		
		// B段
		System.out.println("child thread is resume.");
		thread.resume();
		
		System.out.println("main thread sleep 5s.");
		Thread.sleep(5000);
		System.out.println("child thread is suspend.");
		thread.suspend();
		System.out.println("Current time = " + System.currentTimeMillis() + ", i = " + thread.getI());
		
		System.out.println("main thread sleep 5s.");
		Thread.sleep(5000);
		System.out.println("Current time = " + System.currentTimeMillis() + ", i = " + thread.getI());
		
		thread.stop();
	}
	
	/**
	 * >> suspend();方法和resume();方法的缺陷-独占。
	 * 
	 * @throws InterruptedException
	 */
	public static void testSuspendThread2() throws InterruptedException {
		
		final SuspendSynchronizedObject synchronizedObject = new SuspendSynchronizedObject();
		
		Thread thread1 = new Thread() {
			@Override
			public void run() {
				synchronizedObject.printMessage();
			};
		};
		
		thread1.setName("thread-a");
		thread1.start();
		
		Thread.sleep(1000);
		
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				
				System.out.println("线程" + this.getName() + "正在尝试进入printMessage();方法.");
				synchronizedObject.printMessage();
				System.out.println("线程" + this.getName() + "运行结束.");
			}
		};
		
		thread2.setName("thread-b");
		thread2.start();
		
	}
	
	
	/**
	 * >> suspend();方法和resume();方法的缺陷-独占。
	 * 
	 *    程序将永远不会运行结束。
	 * 
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void testSuspendThread3() throws InterruptedException {
		
		SuspendThread1 thread = new SuspendThread1();
		thread.start();
		Thread.sleep(1000);
		thread.suspend();
		System.out.println("线程" + Thread.currentThread().getName() + "终止。");
	}
	
	
	/**
	 * >> suspend();方法和resume();方法的缺陷-独占。
	 * 
	 *    线程main一般输出不了终止提示，因为PrintStream的println();方法的同步锁被子线程永远占用。
	 *    
	 * @throws InterruptedException
	 */
	@SuppressWarnings("deprecation")
	public static void testSuspendThread4() throws InterruptedException {
		
		SuspendThread2 thread = new SuspendThread2();
		thread.start();
		
		Thread.sleep(1000);
		
		thread.suspend();
		System.out.println("线程" + Thread.currentThread().getName() + "终止。");

	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		testSuspendThread4();
	}
	
	
}


class SuspendThread1 extends Thread {
	
	private long i = 0;

	public long getI() {
		return i;
	}

	public void setI(long i) {
		this.i = i;
	}
	
	@Override
	public void run() {

		while (true) {
			i++;
		}
	}
}


class SuspendThread2 extends Thread {
	
	private long i = 0;
	
	@Override
	public void run() {
		
		while(true) {
			i++;
			System.out.println("i = " + i);
		}
	}
}

class SuspendSynchronizedObject {
	
	@SuppressWarnings("deprecation")
	synchronized public void printMessage() {
		
		System.out.println("begin");
		if (Thread.currentThread().getName().equals("thread-a")) {
			System.out.println("线程thread-a永远被suspend了！");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}



