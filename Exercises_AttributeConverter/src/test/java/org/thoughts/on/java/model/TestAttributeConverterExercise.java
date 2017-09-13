package org.thoughts.on.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAttributeConverterExercise {

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
	 * Implement an AttributeConverter that persists the ReviewText class as a String. 
	 */
	@Test
	public void exercise3() {
		log.info("... exercise3 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		ReviewText text = new ReviewText();
		text.setTitle("This is my title");
		text.setText("And this a loooooooooooooooooooong text ....");
		
		Review r = new Review();
		r.setText(text);
		em.persist(r);

		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Review r2 = em.find(Review.class, r.getId());
		Assert.assertEquals(text.getTitle(), r2.getText().getTitle());
		Assert.assertEquals(text.getText(), r2.getText().getText());
		
		em.getTransaction().commit();
		em.close();
	}
}
