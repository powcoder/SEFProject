https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProductController;
import controller.UserController;
import model.User;

public class SupermarketSystem implements Observer {
	private ProductController productController;
	private UserController userController;
	
	private User currentUser;
	
	private Scanner scanner;
	
	public SupermarketSystem() {
		productController = new ProductController(this);
		userController = new UserController(this);
		scanner = new Scanner(System.in);
		currentUser = null;
	}
	
	public void run() {
		productController.load();
		userController.load();
		login();
		while(true) {
			boolean quit = false;
			listCommands();
			System.out.println();
			System.out.print("Enter Command: ");
			String input = scanner.nextLine();
			switch(input) {
				case "search":
					System.out.print("Enter Product ID: ");
					int productID = scanner.nextInt();
					scanner.nextLine();
					System.out.println();
					productController.displayProduct(productID);
					break;
				case "list":
					System.out.println();
					productController.displayAllProducts();
					break;
				case "buy":
					System.out.print("Enter Product ID: ");
					int productID1 = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Quantity: ");
					int quantity1 = scanner.nextInt();
					scanner.nextLine();
					productController.addToCart(productID1, quantity1);
					System.out.println();
					productController.displayCart(currentUser);
					break;
				case "card":
					System.out.println();
					userController.displayCardInfo();
					break;
				case "cart":
					System.out.println();
					productController.displayCart(currentUser);
					break;
				case "checkout":
					System.out.println();
					productController.checkOut(currentUser);
					break;
				case "replenish":
					System.out.println();
					System.out.print("Enter Product ID: ");
					int productID2 = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Enter Quantity: ");
					int quantity2 = scanner.nextInt();
					scanner.nextLine();
					productController.replenish(productID2, quantity2);
					break;
				case "quit":
					productController.save();
					userController.save();
					quit = true;
					break;
				default:
					printInvalidInput();
			}
			if (quit) {
				break;
			}
		}
	}
	
	public void login() {
		while(true) {
			System.out.print("Enter your ID: ");
			try {
				String input = scanner.nextLine();
				userController.login(Integer.parseInt(input));
			}
			catch(Exception e) {
				printInvalidInput();
				continue;
			}
			System.out.println("Login successful.");
			System.out.println();
			break;
		}
	}
	
	public void listCommands() {
		System.out.println("Welcome! Here is a list of commands that you can use:");
		System.out.println("-> search - Searches for a product with the given ID");
		System.out.println("-> list - Lists all available products for purchase");
		System.out.println("-> buy - Adds a product with the given ID to the cart");
		System.out.println("-> card - Displays card information");
		System.out.println("-> cart - Lists all current buy orders");
		System.out.println("-> checkout - Checks out everything in the cart");
		System.out.println("-> replenish - Replenishes stock level for the given ID");
		System.out.println("-> quit - Quits program");
	}
	
	public void printInvalidInput() {
		System.out.println("Invalid input.");
		System.out.println();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof User) {
			currentUser = (User) arg;
		}
		else if (arg instanceof String) {
			System.out.println(arg);
		}
	}
}
