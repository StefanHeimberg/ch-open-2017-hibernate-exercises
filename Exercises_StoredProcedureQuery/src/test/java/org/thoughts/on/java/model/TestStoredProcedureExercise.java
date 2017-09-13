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
        
            final StoredProcedureQuery p = em.createNamedStoredProcedureQuery("calculate");
            p.setParameter("x", 4d);
            p.setParameter("y", 5d);
            p.execute();
       
		Double sum = (Double) p.getOutputParameterValue("sum");
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
       
            final StoredProcedureQuery p = em.createStoredProcedureQuery("get_reviews", Review.class);
		p.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
		p.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
            
            p.setParameter(2, 2l);
            p.execute();
            
        // call the get_reviews stored procedure for the Book with id 2
		List<Review> reviews = p.getResultList();
		for (Review r : reviews) {
			log.info(r);
		}
        
        em.getTransaction().commit();
        em.close();
	}
}
