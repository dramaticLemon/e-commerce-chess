package com.join.tab.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Item;
import com.join.tab.dto.ItemUserViewDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * create item
 * if exists item by name
 * delete by Id
 * delete by Name
 * update item
 * get All items
 * get item by id
 * 
*/
@Repository
public class ItemRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Item> getAllItem() {
		return entityManager.createQuery(
			"select i from Item i join fetch i.category",
			Item.class
		).getResultList();
	}

	@Transactional
	public void save(Item item) {
		entityManager.persist(item);
	}

	public boolean existsByName(String name) {
        String jpql = "SELECT COUNT(i) FROM Item i WHERE i.name = :name";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("name", name);
        Long count = query.getSingleResult();
        return count > 0;
    }


	@Transactional
	public void deleteById(Long id) {
		Item item = entityManager.find(Item.class, id);
		deleteItem(item);
	}
	
	@Transactional
	public void deleteByName(String name) {
		String jpql = "select i from Item i where i.name = :name";
		TypedQuery<Item> query = entityManager.createQuery(jpql, Item.class);
		query.setParameter("name", name);
		try {
			Item item = query.getSingleResult();
			deleteItem(item);
		} catch (NoResultException e) {
		}
	}

	private void deleteItem(Item item) {
		if (item != null) {
			entityManager.remove(item);
		}
	}

	@Transactional
	public void update(Item item) {
		entityManager.merge(item);
	}
	
	public Item getItem(Long id) {
		return entityManager.find(Item.class, id);
	}
	
	public List<ItemUserViewDto> getAllItemsForUser() {
		return entityManager.createQuery(
			"select i from Item i join fetch i.category",
			ItemUserViewDto.class
		).getResultList();
	}
	
}
