package com.accenture.lkm.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.PurchaseEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
@Transactional
public class PurchaseDAOTest {

	@Autowired
	private PurchaseDAO purchaseDAO;

	@Test
	public void testSavePurchaseDetail() throws Exception {

		// Arrange
		PurchaseEntity entity = new PurchaseEntity();
		entity.setVendorName("ABC Traders");
		entity.setMaterialCategoryId("CAT01");
		entity.setMaterialTypeId("TYPE01");
		entity.setBrandName("BrandX");
		entity.setUnitId("UNIT01");
		entity.setQuantity(10);
		entity.setPurchaseAmount(5000.0);
		entity.setPurchaseDate(new Date());
		entity.setStatus("ACTIVE");

		// Act
		PurchaseEntity savedEntity = purchaseDAO.savePurchaseDetail(entity);

		// Assert
		assertNotNull(savedEntity);
		assertNotNull(savedEntity.getPurchaseId());
		assertTrue(savedEntity.getPurchaseId() > 0);
	}
}
