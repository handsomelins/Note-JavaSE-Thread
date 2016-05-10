package cn.saicent.thread;

public class DespositThread extends Thread{

	private Account account;
	private double depositAccount;
	
	
	public DespositThread(String name, Account account, double depositAccount) {
		
		super(name);
		this.account = account;
		this.depositAccount = depositAccount;
	}
	
	public void run() {
		
		for(int i = 0; i < 100; i++) {
			
			try {
				
				account.deposit(depositAccount);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

}
