https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package model;

public class Product {
	private String name;
	private double weight;
	private double price;
	private int stockShelf;
	private int currentStockShelf;
	private int stockWarehouse;
	private int replenishment;
	private int reorder;
	private String supplierName;
	private double revenue;
	
	public Product(String name, double weight, double price, int stockShelf, int stockWarehouse, int replenishment, int reorder, String supplierName) {
		this.name = name;
		this.weight = weight;
		this.price = price;
		this.stockShelf = stockShelf;
		this.currentStockShelf = stockShelf;
		this.stockWarehouse = stockWarehouse;
		this.replenishment = replenishment;
		this.reorder = reorder;
		this.supplierName = supplierName;
		revenue = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getShelfStock() {
		return stockShelf;
	}
	
	public int getCurrentStockShelf() {
		return currentStockShelf;
	}
	
	public void setCurrentStockShelf(int currentStockShelf) {
		this.currentStockShelf = currentStockShelf;
	}
	
	public void setShelfStock(int quantity) {
		stockShelf = quantity;
	}
	
	public int getWarehouseStock() {
		return stockWarehouse;
	}
	
	public void setWarehouseStock(int quantity) {
		stockWarehouse = quantity;
	}
	
	public int getReplenishment() {
		return replenishment;
	}
	
	public int getReorder() {
		return reorder;
	}
	
	public String getSupplier() {
		return supplierName;
	}
	
	public double getRevenue() {
		return revenue;
	}
	
	public void addToRevenue(double value) {
		revenue += value;
	}
}
