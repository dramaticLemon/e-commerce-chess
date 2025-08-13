package com.join.tab.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.join.tab.domain.Order;
import com.join.tab.event.ItemAddedToCartEvent;
import com.join.tab.services.CartService;

@Component
public class CartEventListener{
	
	@Autowired
	private CartService cartService;

	@EventListener
	public void handleItemToCart(ItemAddedToCartEvent event) {
		Order order = cartService.getOrCreateOrder(event.getSessionId(), event.getUser());
		cartService.addItemToCart(order, event.getItemId(), event.getQuantity());
	}
}
