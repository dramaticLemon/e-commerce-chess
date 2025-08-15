package com.join.tab.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.join.tab.domain.Item;
import com.join.tab.domain.Order;
import com.join.tab.domain.OrderItem;
import com.join.tab.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class OrderRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	public Optional<Order> findActiveOrderByUser(User user) {
		try {
			Order order = entityManager.createQuery(
				"select o from Order o where o.user = :user and o.isActive = true", Order.class)
				.setParameter("user", user)
				.getSingleResult();
				return Optional.of(order);

		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Transactional
	public Order save(Order order) {
		if (order.getId() == null) {
			entityManager.persist(order);

			return order;
		} else {
			return entityManager.merge(order);
		}
	}

	public Optional<OrderItem> findOrderItem(Order order, Item item) {
		try {
			OrderItem orderItem = entityManager.createQuery(
				"select oi from OrderItem oi where oi.order = :order and oi.item = :item",
				OrderItem.class)
				.setParameter("order", order)
				.setParameter("item", item)
				.getSingleResult();
			
				return Optional.of(orderItem);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
	@Transactional
	public void saveOrderItem(OrderItem orderItem) {
		if (orderItem.getId() == null) {
			entityManager.persist(orderItem);
		} else {
			entityManager.merge(orderItem);
		}
	}
}
