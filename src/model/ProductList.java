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

public class ProductList extends Observable{
	private ArrayList<Product> products;
	private ArrayList<Discount> discounts;
	private ArrayList<BuyOrder> buyOrders;
	private static int previousID = 0;
	private static int previousQuantity = 0;
	
	public ProductList() {
		products = new ArrayList<Product>();
		discounts = new ArrayList<Discount>();
		buyOrders = new ArrayList<BuyOrder>();
	}
	
	public void addProduct(String productName, double weight, double price, int shelfStock, int warehouseStock, int replenishment, int reorder, String supplierName) {
		Product product = new Product(productName, weight, price, shelfStock, warehouseStock, replenishment, reorder, supplierName);
		products.add(product);
	}
	
	public void addDiscount(int ID, int quantity, double percentage) {
		Discount discount = new Discount(ID, quantity, percentage);
		discounts.add(discount);
	}
	
	public void addBuyOrder(int productID, int quantity) {
		if (productID == previousID && quantity + previousQuantity > getProductByID(productID).getCurrentStockShelf()) {
			setChanged();
			notifyObservers("Quantity exceeds available stock \n");
           	return;
        }
		else{
            BuyOrder buyOrder = new BuyOrder(productID, quantity);
            buyOrders.add(buyOrder);
            getProductByID(productID).setCurrentStockShelf(products.get(productID).getCurrentStockShelf() - quantity);
            previousID = productID;
            previousQuantity = quantity;
       }
	}

	private String toString(int index) {
		Product product = products.get(index);
		return ("ID: " + index + "\n" + 
				"Name: " + product.getName() + "\n" +
				"Weight: " + product.getWeight() + "kg \n" +
				"Price: $" + product.getPrice() + "\n" +
				"Stock Remaining: " + product.getCurrentStockShelf() + "\n" +
				"Warehouse Stock: " + product.getWarehouseStock() + "\n" +
				"Supplier: " + product.getSupplier() + "\n" +
				"Revenue: $" + product.getRevenue() + "\n");
	}
	
	private String listProducts() {
		String output = "";
		for (int i = 0; i < products.size(); i++) {
			output = output + i + " " + products.get(i).getName() + "\n";
		}
		return output;
	}

	private String listCart(User currentUser) {
		String output = "#Shopping Cart# \n";
		for (int i = 0; i < buyOrders.size(); i++) {
			int productID = buyOrders.get(i).getProductID();
			output = output + products.get(productID).getName() + " x " + buyOrders.get(i).getQuantity() + " = $" + getSubtotal(buyOrders.get(i)) + "\n";
		}
		output = output + "Total = $" + getTotal() + "\n" + "Discounted Total = $" + getDiscountedTotal(currentUser) + "\n";
		return output;
	}
	
	private double getSubtotalDiscount(BuyOrder buyOrder){
		int productID = buyOrder.getProductID();
		int quantity = buyOrder.getQuantity();
		int discountID = -1;
		double subtotalDiscount = 0;
		for (int i = 0; i < discounts.size(); i++) {
			if (discounts.get(i).getProductID() == productID) {
				discountID = i;
				break;
			}
		}
		if (discountID == -1) {
			return 0;
		}
		if (quantity >= discounts.get(discountID).getQuantity()) {
			subtotalDiscount = (products.get(productID).getPrice() * buyOrder.getQuantity()) * discounts.get(discountID).getPercentage();
		}
		return subtotalDiscount;
	}
	
	public double getTotalDiscount() {
		double totalDiscount = 0;
		for (int i = 0; i < buyOrders.size(); i++) {
			totalDiscount += getSubtotalDiscount(buyOrders.get(i));
		}
		return totalDiscount;
	}
	
	private double getSubtotal(BuyOrder buyOrder) {
		int productID = buyOrder.getProductID();
		double subtotal = products.get(productID).getPrice() * buyOrder.getQuantity();
		return subtotal;
	}
	
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < buyOrders.size(); i++) {
			total += getSubtotal(buyOrders.get(i));
		}
		return total;
	}
	
	private double getDiscountedTotal(User currentUser) {
		double discountedTotal = getTotal() - getTotalDiscount();
		Customer customer = (Customer) currentUser;
		int currentLoyalty = customer.getLoyalty();
		for (int i = 20; i <= currentLoyalty;) {
			discountedTotal -=5;
			currentLoyalty -=20;
		}
		return discountedTotal;	
	}
	
	public Product getProductByID(int productID) {
		return products.get(productID);
	}
	
	private void resetCart() {
		buyOrders.clear();
	}
	
	public void getProduct(int input) {
		setChanged();
        notifyObservers(toString(input));
	}

	public void getAllProducts() {
		setChanged();
        notifyObservers(listProducts());
	}

	public void getBuyOrders(User currentUser) {
		setChanged();
		notifyObservers(listCart(currentUser));
	}

	public void checkOut(User currentUser) {
		Customer customer = (Customer) currentUser;
		double currentBalance = customer.getBalance();
		for (int i = 0; i < buyOrders.size(); i++) {
			BuyOrder buyOrder = buyOrders.get(i);
			Product product = products.get(buyOrder.getProductID());
			product.setShelfStock(product.getShelfStock() -  buyOrder.getQuantity());
			product.addToRevenue(getSubtotal(buyOrder));
		}
		double discountedTotal = getDiscountedTotal(currentUser);
		customer.setBalance(currentBalance - discountedTotal);
		int currentLoyalty = customer.getLoyalty();
		for (int i = 20; i <= currentLoyalty;) {
			customer.setLoyalty(currentLoyalty -= 20);
		}
		for (int i = 10; i <= discountedTotal;) {
			discountedTotal -= 10;
			customer.setLoyalty(currentLoyalty +=1);
		}
		resetCart();
	}

	public void replenish(int productID, int quantity) {
		if (quantity > getProductByID(productID).getWarehouseStock()){
			setChanged();
			notifyObservers("Not enough warehouse stock \n");
			return;
		}
		Product product = products.get(productID);
		product.setWarehouseStock(product.getWarehouseStock() - quantity);
		product.setShelfStock(product.getShelfStock() + quantity);
		product.setCurrentStockShelf(product.getCurrentStockShelf() + quantity);
		setChanged();
		notifyObservers("Stock of " + product.getName() + " replenished by " + quantity + "\n");
	}
	
	public void reorder() {
		
	}
}
