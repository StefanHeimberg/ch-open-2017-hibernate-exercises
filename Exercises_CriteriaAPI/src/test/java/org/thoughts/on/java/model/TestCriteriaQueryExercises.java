package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCriteriaQueryExercises {

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
	 * Use the Criteria API to select Author entities by their firstName and lastName attribute.
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		
		// define query
		
		
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("firstname", "John");
		q.setParameter("lastname", "Doe");
		
		Author a = q.getSingleResult();
		Assert.assertEquals("John", a.getFirstName());
		Assert.assertEquals("Doe", a.getLastName());
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Use the Criteria API to select all Author entities in alphabetical order who wrote the Book with a given id.
	 */
	@Test
	public void exercise4() {
		log.info("... exercise4 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);

		// define query
		
		
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("bookId", 1L);
		
		List<Author> authors = q.getResultList();
		Assert.assertEquals("Author", authors.get(0).getLastName());
		log.info(authors.get(0).getLastName());
		Assert.assertEquals("Doe", authors.get(1).getLastName());
		log.info(authors.get(1).getLastName());
		
		em.getTransaction().commit();
		em.close();
	}
}
