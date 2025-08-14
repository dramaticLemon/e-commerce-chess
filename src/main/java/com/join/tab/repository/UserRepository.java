package com.join.tab.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.join.tab.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository {

	private static final Logger log = LoggerFactory.getLogger(UserRepository.class);


	@PersistenceContext
	private EntityManager entityManager;

	public User findByUserEmail(String email) {
		TypedQuery<User> query = entityManager.createQuery(
			"select u from User u where u.email = :email", User.class
		);
		query.setParameter("email", email);

		return query.getResultStream().findFirst().orElse(null);
	}
	
	public User findByUserName(String username) {
		TypedQuery<User> query = entityManager.createQuery(
			"select u from User u where u.name = :name", User.class
		);
		query.setParameter("name", username);

		return query.getResultStream().findFirst().orElse(null);
	} 

	@Transactional
	public void save(User user) {
		log.info("call save method user repository");
		entityManager.persist(user);
	}

}
