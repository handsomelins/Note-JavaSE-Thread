package cn.saicent.thread;

public class DrawThread extends Thread {

	private Account account;
	private double drawAccount;
	
	public DrawThread(String name, Account account, double drawAccount) {
		
		super(name);
		this.account = account;
		this.drawAccount = drawAccount;
	}

	@Override
	public void run() {
	 
		for(int i = 0; i < 100; i++) {
			
			try {
				
				account.draw(drawAccount);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

}
