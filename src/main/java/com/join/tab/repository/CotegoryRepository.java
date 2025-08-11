package com.join.tab.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.join.tab.domain.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CotegoryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Category category) {
		entityManager.persist(category);
	}

	@Transactional
	public void deleteById(Long id) {
		Category category = entityManager.find(Category.class, id);
		if (category != null) {
			entityManager.remove(category);
		}
	}
	
	@Transactional
	public void update(Category category) {
		entityManager.merge(category);
	}
	
	public Category getCategory(Long id) {
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> getAllCategory() {
		return entityManager.createQuery(
			"select c from Category c",
			Category.class
		).getResultList();
	}

	public boolean existsByNameOrCode(String name, String code) {
        String jpql = "SELECT COUNT(c) FROM Category c WHERE c.name = :name OR c.code = :code";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("name", name);
        query.setParameter("code", code);
        Long count = query.getSingleResult();
        return count > 0;
    }

}
