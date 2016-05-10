package cn.saicent.thread;

public class Account {

	private String accountNo;
	private double balance;
	private boolean flag = false;
	
	public Account(){}
	
	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	
	public synchronized void draw(double drawAmount) throws InterruptedException {
		
		Thread.sleep(1000);
		if(!flag) {
			System.out.println("无法取钱");
			wait();
		} else {
			System.out.println(Thread.currentThread().getName() + "取钱：" + drawAmount);
			this.balance -= drawAmount;
			System.out.println("账户余额为：" + balance);
			flag = false;
			notifyAll();
		}
		System.out.println("取钱");
	}
	
	
	public synchronized void deposit(double depositAccount) throws InterruptedException {
		
		Thread.sleep(1000);
		if(flag) {
			System.out.println("无法存钱");
			wait();
		} else {
			System.out.println(Thread.currentThread().getName() + "存款：" + depositAccount);
			this.balance += depositAccount;
			System.out.println("账户余额为：" + balance);
			flag = true;
			notifyAll();
		}
		System.out.println("存钱");
	}
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
