https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package controller;

import model.UserList;
import view.SupermarketSystem;

public class UserController {
	
	private UserList userList;
	
	public UserController(SupermarketSystem supermarketSystem) {
		userList = new UserList();
		userList.addObserver(supermarketSystem);
	}
	
	public void load() {
		userList.addCustomer("John", 100, 40);
	}
	
	public void save() {
		
	}

	public void login(int input) {
		userList.login(input);
	}

	public void displayCardInfo() {
		userList.getCardInfo();
	}


}
