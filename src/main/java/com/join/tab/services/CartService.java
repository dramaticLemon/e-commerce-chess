package com.join.tab.services;

import java.util.ArrayList;


import org.springframework.stereotype.Service;

import com.join.tab.domain.Item;
import com.join.tab.domain.Order;
import com.join.tab.domain.OrderItem;
import com.join.tab.domain.User;
import com.join.tab.dto.OrderItemDto;
import com.join.tab.repository.ItemRepository;
import com.join.tab.repository.OrderRepository;
import com.join.tab.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	// private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final ItemRepository itemRepository;

	public CartService(
		UserRepository userRepository,
		OrderRepository orderRepository,
		ItemRepository itemRepository
		) {
		this.itemRepository = itemRepository;
		// this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	@Transactional
	public void addItemToCart(User user, OrderItemDto dto) {
			Order order = orderRepository.findActiveOrderByUser(user)
					.orElseGet(() -> {
						Order newOrder = new Order();
						newOrder.setUser(user);
						newOrder.setActive(true);
						newOrder.setItems(new ArrayList<>());
						orderRepository.save(newOrder);
						return newOrder;
					});

			Item item = itemRepository.getItem(dto.getId());

			OrderItem orderItem = orderRepository.findOrderItem(order, item)
					.orElseGet(() -> {
						OrderItem oi = new OrderItem();
						oi.setOrder(order);
						oi.setItem(item);
						oi.setPriceAtPurchase(item.getPrice());
						orderRepository.saveOrderItem(oi);
						order.getItems().add(oi);
						return oi;
					});

			orderItem.setQuantity(orderItem.getQuantity() + 1);
			orderRepository.save(order);
	}

	
	
	// public event

	
	
}
