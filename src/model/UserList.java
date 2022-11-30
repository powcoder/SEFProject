https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package model;

import java.util.ArrayList;
import java.util.Observable;

public class UserList extends Observable{
	private ArrayList<User> users;
	private User currentUser;
	
	public UserList() {
		users = new ArrayList<User>();
	}
	
	public void addCustomer(String userName, double balance, int loyalty) {
		User user = null;
		user = new Customer(userName, balance, loyalty);
		users.add(user);
	}

	public void login(int index) {
		currentUser = users.get(index);
		setChanged();
        notifyObservers(currentUser);
	}

	public void getCardInfo() {
		Customer customer = (Customer) currentUser;
		String output = ("Name: " + customer.getName() + "\n" +
						"Balance: $" + customer.getBalance() + "\n" +
						"Loyalty Points: " + customer.getLoyalty() + "\n"
						);
		setChanged();
		notifyObservers(output);
	}
}
