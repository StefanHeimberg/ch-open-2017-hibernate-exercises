package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDatabaseFunctionExercises {

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
	 * Count all Authors in a JPQL query.
	 */
	@Test
	public void exercise2() {
		System.out.println("... exercise2 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// count the Authors in a JPQL query
		Long count = null;
		log.info("Number of Authors: "+count);

		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Select the title and the publishing year of all Books.
	 */
	@Test
	public void exercise3() {
		System.out.println("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Select the current month in a JPQL query.
		List<Object[]> books = null;
		for (Object[] book :  books) {
			log.info(book[0] + " was published in " + book[1]);
		}

		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Call the custom function calculate with the parameter 3 and 7.
	 */
	@Test
	public void exercise4() {
		System.out.println("... exercise4 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Call the custom function calculate with the parameter 3 and 7.
		Integer result = null;
		log.info("3 + 7 = "+result);

		em.getTransaction().commit();
		em.close();
	}
}
