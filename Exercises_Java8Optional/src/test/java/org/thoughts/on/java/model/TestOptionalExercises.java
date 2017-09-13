package org.thoughts.on.java.model;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestOptionalExercises {

	Logger log = Logger.getLogger(this.getClass().getName());

	private EntityManagerFactory emf;

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}
	
	/**
	 * Make the comment attribute of the Review entity optional.
	 */
	@Test
	public void exercise1() {
		log.info("... exercise1 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Review r = em.find(Review.class, 1L);
		Assert.assertTrue(r.getComment() instanceof Optional);
		Assert.assertFalse(r.getComment().isPresent());
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Use Hibernate’s loadOptional method to load the Review entity with id 128.
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);
		
		// Use Hibernate’s loadOptional method to load the Review entity with id 128.
		Optional<Review> r = null;
		Assert.assertFalse(r.isPresent());
		
		em.getTransaction().commit();
		em.close();
	}
}
