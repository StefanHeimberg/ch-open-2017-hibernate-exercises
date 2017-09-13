package org.thoughts.on.java.model;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLifecycleEventExercises {

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
	 * Implement a lifecycle callback method for the Book entity 
	 * that updates the lastUpdate attribute on every insert and update operation.
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Book b = new Book();
		b.setTitle("My Book");
		em.persist(b);
		LocalDateTime lastUpdate = b.getLastUpdate();
		Assert.assertNotNull(lastUpdate);
		log.info("lastUpdate: "+lastUpdate);
		
		b.setTitle("A book about JPA ...");
		em.flush();
		Assert.assertNotEquals(lastUpdate, b.getLastUpdate());
		log.info("lastUpdate: "+b.getLastUpdate());
		
		em.getTransaction().commit();
		em.close();
	}
}
