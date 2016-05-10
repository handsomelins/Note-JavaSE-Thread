package cn.saicent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		
		for(int i = 0; i < 100; i++) {
			System.out.println(i);
		}
		return 100;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTest());
		System.out.println(futureTask.get());
		new Thread(futureTask).start();
	}
	

}
