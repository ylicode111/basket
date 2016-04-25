package com.mycompany.assignment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket 
{
	private Map<Product, BasketItem> allItems=new HashMap<Product, BasketItem>();	

	public void add(BasketItem item) {
		if(item==null){
			throw new IllegalArgumentException("Attempting to add null item to basket.");
		}
		BasketItem existingItem=allItems.get(item.getProduct());
		if (existingItem!=null){
			existingItem.setQuantity(existingItem.getQuantity().add(item.getQuantity()));
		}
		else{
			allItems.put(item.getProduct(), item );
		}
	}

	
	public List<BasketItem> getItems() {
		return new ArrayList<BasketItem>(allItems.values());
	}

	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice=BigDecimal.valueOf(0.0d);
		for (BasketItem item: allItems.values()){
			totalPrice=totalPrice.add(item.getQuantity().multiply(item.getProduct().getPrice()));
		}
		return totalPrice;
	}

	public void update(BasketItem updatedItem) {
		if(updatedItem==null){
			throw new IllegalArgumentException("No update item specififed.");
		}
		allItems.remove(updatedItem.getProduct());
		if (updatedItem.getQuantity().doubleValue()!=0.0d){
			allItems.put(updatedItem.getProduct(), updatedItem );
		}
	}
		
}
