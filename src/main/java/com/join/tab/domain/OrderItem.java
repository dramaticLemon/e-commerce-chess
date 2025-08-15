package com.join.tab.domain;


import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	@NotNull(message="Order cannot be null")
	private Order order;

	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	@NotNull(message="Item cannot be null")
	private Item item;

	@Min(value=1, message="Quantity must be at least 1")
	@Column(name="quantity", nullable=false)
	private int quantity;

	@NotNull(message="Price at purchase cannot be null")
	@Min(value=0, message="Price at purchase must be non-negative")
	@Column(name="price_at_purchase", nullable=false, precision = 10, scale = 2)
	private BigDecimal priceAtPurchase;
	
	public OrderItem() {
	}

	public OrderItem(Long id, Order order, Item item, int quantity, BigDecimal priceAtPurchase) {
		this.id = id;
		this.order = order;
		this.item = item;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", intem=" + item + ", quantity=" + quantity
				+ ", priceAtPurchase=" + priceAtPurchase + "]";
	}

	
}
