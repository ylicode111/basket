package com.mycompany.assignment;

import java.math.BigDecimal;

import org.junit.Test;

public class PrductTest {

	@Test(expected=IllegalArgumentException.class)
	public void testProductWithNullType() {
		new Product(null, BigDecimal.valueOf(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProductWithNullPrice() {
		new Product(ProductType.Apple,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProductWithNullTypeAndPrice() {
		new Product(null, null);
	}

}
