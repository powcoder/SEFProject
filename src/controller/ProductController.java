https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package controller;

import model.ProductList;
import model.User;
import view.SupermarketSystem;

public class ProductController {
	
	private ProductList productList;
	
	public ProductController(SupermarketSystem supermarketSystem) {
		productList = new ProductList();
		productList.addObserver(supermarketSystem);
	}
	
	public void load() {
		productList.addProduct("Apple", 0.1, 1, 20, 100, 50, 100, "Fresh Fruits.co");
		productList.addProduct("Banana", 0.11, 1.5, 10, 100, 50, 100, "Fresh Fruits.co");
		productList.addDiscount(0,3,0.1);
		productList.addDiscount(1,2,0.2);
	}
	
	public void save() {
		
	}

	public void displayProduct(int input) {
		productList.getProduct(input);
	}
	
	public void displayAllProducts() {
		productList.getAllProducts();
	}

	public void displayCart(User currentUser) {
		productList.getBuyOrders(currentUser);
	}

	public void addToCart(int productID, int quantity) {
		productList.addBuyOrder(productID, quantity);
	}

	public void checkOut(User currentUser) {
		productList.checkOut(currentUser);
	}

	public void replenish(int productID, int quantity) {
		productList.replenish(productID, quantity);
	}
}
