https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package TestCase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.Product;
import model.ProductList;
import model.User;

class TestStockLevel {
	
	User user;
	ProductList productlist;
	Product product;

	@BeforeEach
	void setUp() {
		//add customer
		user = new Customer("Minh", 100, 0);
		productlist = new ProductList();
		productlist.addProduct("Durian", 0.1, 1, 20, 100, 50, 100, "Fresh Fruits.co");
		product = productlist.getProductByID(0);
		productlist.addBuyOrder(0, 20 );
	}

	@Test
	void testreducestock() {
		//checkout
		productlist.checkOut(user);	
		assertEquals(0, product.getShelfStock());
	}
	
	@Test
	void testreplenishstock() {
		productlist.replenish(0, 12);
		assertEquals(32, product.getShelfStock());
	}
	
	@Test
	void testtotal() {
		assertEquals(20, productlist.getTotal());
		
	}

}
