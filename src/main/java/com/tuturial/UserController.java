package com.tuturial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserController{

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	private User neuerUser = null;
	

	public User getNeuerUser() {
		if(null == neuerUser) {
			this.neuerUser = new User();
		}
		
		return this.neuerUser;
	}
	

	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("ghost");
	}
	
	// speichern
	public User create(User user) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	
		
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return user;
	

}}
