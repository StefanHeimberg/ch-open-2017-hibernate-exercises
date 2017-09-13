package org.thoughts.on.java.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDateAndTimeExercises {

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
	 * Persist the lastUpdate timestamp of the Review entity as a LocalDateTime.
	 */
	@Test
	public void exercise2() {
		log.info("... exercise2 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Review r = em.find(Review.class, 1L);
		r.setLastUpdate(LocalDateTime.now());
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Select all reviews that were posted in 2016.
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Select all reviews that were posted in 2016.
		TypedQuery<Review> q = null;
		List<Review> reviews = q.getResultList();
		Assert.assertEquals(2, reviews.size());
		
		em.getTransaction().commit();
		em.close();
	}
}
