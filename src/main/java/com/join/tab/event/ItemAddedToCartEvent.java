package com.join.tab.event;

import org.springframework.context.ApplicationEvent;

import com.join.tab.domain.User;


public class ItemAddedToCartEvent extends ApplicationEvent {
		
	private final Long itemId;
	private final int quantity;
	private final String sessionId;
	private final User user; // null для гостей

	public ItemAddedToCartEvent(Object source, Long itemId, int quantity, String sessionId, User user) {
		super(source);
		this.itemId = itemId;
		this.quantity = quantity;
		this.sessionId = sessionId;
		this.user = user;
	}

	public Long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getSessionId() {
		return sessionId;
	}

	public User getUser() {
		return user;
	}

	
}
