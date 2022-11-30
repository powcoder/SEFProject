https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package model;

public class Customer extends User{

	private double balance;
	private int loyalty;
	
	public Customer(String name, double balance, int loyalty) {
		super(name);
		this.balance = balance;
		this.loyalty = loyalty;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double value) {
		balance = value;
	}
	
	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int value) {
		loyalty = value;
	}
}
