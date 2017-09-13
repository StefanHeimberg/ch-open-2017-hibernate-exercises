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

public class TestEntityListenerExercises {

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
	 * Implement an EntityListener that uses the AuditedEntity interface 
	 * to update the lastUpdate attribute of the Author and Publication entity 
	 * on every insert and update operation.
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		log.info("Book");
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
		
		
		log.info("Author");
		Author a = new Author();
		a.setFirstName("John");
		a.setLastName("Doe");
		em.persist(a);
		lastUpdate = a.getLastUpdate();
		Assert.assertNotNull(lastUpdate);
		log.info("lastUpdate: "+lastUpdate);
		
		a.setFirstName("Jane");
		em.flush();
		Assert.assertNotEquals(lastUpdate, a.getLastUpdate());
		log.info("lastUpdate: "+a.getLastUpdate());
		em.getTransaction().commit();
		em.close();
	}
}
