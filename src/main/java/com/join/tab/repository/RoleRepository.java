package com.join.tab.repository;

import org.springframework.stereotype.Repository;

import com.join.tab.domain.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Role findRoleById(Long id) {
		return entityManager.find(Role.class, id);
	}
}
