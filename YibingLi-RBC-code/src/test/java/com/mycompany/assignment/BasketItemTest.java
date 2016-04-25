package com.mycompany.assignment;
import java.math.BigDecimal;

import org.junit.Test;

public class BasketItemTest {

	@Test(expected=IllegalArgumentException.class)
	public void testItemWithNullProduct() {
		new BasketItem(null, BigDecimal.valueOf(120));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testItemWithNullQuantity() {
		new BasketItem(new Product(ProductType.Banana, BigDecimal.valueOf(1.5d)), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testItemWithNullProductAndQuantity() {
		new BasketItem(null, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testItemWithNegativeQuantity() {
		new BasketItem(new Product(ProductType.Banana, BigDecimal.valueOf(1.5d)), BigDecimal.valueOf(-20.1d));
	}
	
}
