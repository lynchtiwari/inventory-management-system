package com.accenture.lkm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for PurchaseDAO to perform the CRUD operation on
 * Purchase table <br/>
 *
 */
@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	private static Logger LOGGER = Logger.getLogger(PurchaseDAOImpl.class);

	// Auto wire EntityManagerFactory here
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	/*
	 * This method inserts the Purchase Data into the Purchase table.
	 * 
	 * @param purchaseEntity
	 * 
	 * @return PurchaseEntity
	 */
	@Override
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(purchaseEntity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return purchaseEntity;
	}

}
