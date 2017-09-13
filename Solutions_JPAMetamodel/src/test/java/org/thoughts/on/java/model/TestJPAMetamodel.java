package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJPAMetamodel {

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
	 * Use the JPA Metamodel to reference the lastname attribute of the Author entity in the WHERE clause of the query.
	 */
	@Test
	public void exercise2() {
		log.info("... exercise2 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		Root<Author> author = cq.from(Author.class);
		cq.select(author);
		
		ParameterExpression<String> lastname = cb.parameter(String.class, "lastname");
		cq.where(cb.equal(author.get(Author_.lastName), lastname));
		
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("lastname", "Doe");
		
		Author a = q.getSingleResult();
		Assert.assertEquals("John", a.getFirstName());
		Assert.assertEquals("Doe", a.getLastName());
		
		em.getTransaction().commit();
		em.close();
	}
}
