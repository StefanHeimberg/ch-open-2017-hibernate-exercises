package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

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
                
                Root<Author> author = cq.from(Author.class);

                cq.where(cb.and(
                        cb.equal(author.get(Author_.firstName), cb.parameter(String.class, "p_firstname")),
                        cb.equal(author.get(Author_.lastName), cb.parameter(String.class, "p_lastname"))
                ));
                
                cq.select(author);
		
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("p_firstname", "John");
		q.setParameter("p_lastname", "Doe");
		
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

                final Root<Author> author = cq.from(Author.class);
                final SetJoin<Author, Book> books = author.join(Author_.books);
                
                cq.where(
                        cb.equal(books.get(Book_.id), cb.parameter(Long.class, "p_bookId"))
                );
                
                cq.select(author);
                
                cq.orderBy(cb.asc(author.get(Author_.lastName)));
		
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("p_bookId", 1L);
		
		List<Author> authors = q.getResultList();
		Assert.assertEquals("Author", authors.get(0).getLastName());
		log.info(authors.get(0).getLastName());
		Assert.assertEquals("Doe", authors.get(1).getLastName());
		log.info(authors.get(1).getLastName());
		
		em.getTransaction().commit();
		em.close();
	}
}
