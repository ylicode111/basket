package com.mycompany.assignment;

import java.math.BigDecimal;

public class Product {
	
	private ProductType type;
	private BigDecimal price;
	 
	public Product(ProductType type, BigDecimal price){
		if (type==null ||price==null) throw new IllegalArgumentException("product or price cannot be null.");
		this.type=type;
		this.price=price;
	}
	
	 public ProductType getType() {
		return type;
	}
	
	 
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

		
	
}
