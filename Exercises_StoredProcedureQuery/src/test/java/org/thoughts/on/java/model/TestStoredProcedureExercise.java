package org.thoughts.on.java.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStoredProcedureExercise {

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
	 * Use a @NamedStoredProcedureQuery to call the stored procedure 
	 * calculate with the parameters 4 and 5.
	 */
	@Test
	public void exercise2() {
		System.out.println("... exercise2 ...");
		
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
       
        // call the stored procedure calculate with parameters 4 and 5
		Double sum = null;
		Assert.assertEquals(new Double(9), sum);
		log.info("Calculation result: 4 + 5 = " + sum);
        
        em.getTransaction().commit();
        em.close();
	}

	
	/**
	 * Use a StoredProcedureQuery to call the get_reviews 
	 * stored procedure for the Book with id 2.
	 */
	@Test
	public void exercise3() {
		System.out.println("... exercise3 ...");
		
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
       
        // call the get_reviews stored procedure for the Book with id 2
		List<Review> reviews = null;
		for (Review r : reviews) {
			log.info(r);
		}
        
        em.getTransaction().commit();
        em.close();
	}
}
