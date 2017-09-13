package org.thoughts.on.java.model;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStreamsExercises {

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
	 * Select all Author entities and get the result set as a Stream.
	 */
	@Test
	public void exercise2() {
		log.info("... exercise2 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);
		
		// Select all Author entities and get the result set as a Stream.
		Stream<Author> authors = null;
		Assert.assertEquals(11, authors.count());
		
		em.getTransaction().commit();
		em.close();
	}
}
