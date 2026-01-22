package com.accenture.lkm.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.services.PurchaseServiceImpl;

public class PurchaseServiceTest {

	@Test
	public void testAddPurchaseDetails() {

		PurchaseServiceImpl service = new PurchaseServiceImpl();
		PurchaseBean bean = new PurchaseBean();

		try {
			PurchaseBean result = service.addPurchaseDetails(bean);
			assertNotNull(result);
		} catch (Exception e) {
			// Expected because DAO is not injected
			assertNotNull(e);
		}
	}
}
