package com.mycompany.assignment;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;


public class BasketTest 
{
	private Basket basket;
	
	@Before
	public void init(){
		basket=new Basket();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddNullItem(){
		basket.add(null);
	}
	
	@Test
	public void testAddItemsOfOneType(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		basket.add(apples);
		assertEquals(apples, basket.getItems().get(0));
	}
	
	@Test
	public void testAddItemsOfMixedType(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		BasketItem oranges=new BasketItem(new Product(ProductType.Orange, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(2));
		basket.add(apples);
		basket.add(oranges);
		assertThat(basket.getItems(), hasItems(apples, oranges));
		assertThat(2, is(equalTo(basket.getItems().size())));

	}
	
	@Test
	public void testAddAdditionalQuantity(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		BasketItem moreApples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(5));
		basket.add(apples);
		basket.add(moreApples);
		assertThat(7.0, equalTo(basket.getItems().get(0).getQuantity().doubleValue()));
	}
	

	@Test
	public void testGetTotalPriceOfEmptyBasket(){
		double expectedPrice=0.0d;
		assertThat(expectedPrice, equalTo(basket.getTotalPrice().doubleValue()));
	}
	
	@Test
	public void testGetTotalPriceWithOneItem(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		basket.add(apples);
		double expectedPrice=1.5d*2;
		assertThat(expectedPrice, equalTo(basket.getTotalPrice().doubleValue()));
	}
	
	
	@Test
	public void testGetTotalPriceMultipleItems(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		BasketItem oranges=new BasketItem(new  Product(ProductType.Orange, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(3));
		BasketItem bananas=new BasketItem(new  Product(ProductType.Banana, BigDecimal.valueOf(3.50d)), BigDecimal.valueOf(4));
		BasketItem lemons=new BasketItem(new  Product(ProductType.Lemon, BigDecimal.valueOf(0.50d)), BigDecimal.valueOf(5));
		BasketItem peaches=new BasketItem(new  Product(ProductType.Peach, BigDecimal.valueOf(0.60d)), BigDecimal.valueOf(6));
		basket.add(apples);
		basket.add(oranges);
		basket.add(bananas);
		basket.add(lemons);
		basket.add(peaches);
		double expectedPrice=1.5d*2+2.5d*3+3.5d*4+0.5d*5+0.60d*6;
		assertThat(expectedPrice, equalTo(basket.getTotalPrice().doubleValue()));
	}
	
	@Test
	public void testNormalUpdate(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		basket.add(apples);
		BasketItem updatedApples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(4));
		basket.update(updatedApples);
		assertThat(updatedApples.getQuantity().doubleValue(), equalTo(basket.getItems().get(0).getQuantity().doubleValue()));
	}
	
	@Test
	public void testUpdateWithZeroQuantity(){
		BasketItem oranges=new BasketItem(new Product(ProductType.Orange, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(3));
		basket.add(oranges);
		BasketItem updatedOrangess=new BasketItem(new Product(ProductType.Orange, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(0));
		basket.update(updatedOrangess);
		assertThat(0, equalTo(basket.getItems().size()));
	}
   
	@Test
	public void testUpdateWithNoPreviousEntry(){
		BasketItem oranges=new BasketItem(new Product(ProductType.Banana, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(3));
		basket.update(oranges);
		assertThat(oranges, equalTo(basket.getItems().get(0)));
	}
	
	@Test
	public void testUpdateWithMorePreviousEntries(){
		BasketItem apples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(2));
		basket.add(apples);
		BasketItem oranges=new BasketItem(new Product(ProductType.Orange, BigDecimal.valueOf(2.50d)), BigDecimal.valueOf(3));
		basket.add(oranges);
		BasketItem bananas=new BasketItem(new Product(ProductType.Banana, BigDecimal.valueOf(3.50d)), BigDecimal.valueOf(4));
		basket.add(bananas);
		BasketItem updatedApples=new BasketItem(new Product(ProductType.Apple, BigDecimal.valueOf(1.50d)), BigDecimal.valueOf(4));
		basket.update(updatedApples);
		assertThat(3, equalTo(basket.getItems().size()));
		assertThat(basket.getItems(), hasItems(updatedApples));
	}
	
	
}
