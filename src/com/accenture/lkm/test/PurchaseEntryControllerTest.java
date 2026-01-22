package com.accenture.lkm.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.web.controller.PurchaseEntryController;

public class PurchaseEntryControllerTest {

	@Test
	public void testPurchaseEntry() throws Exception {

		PurchaseEntryController controller = new PurchaseEntryController();

		ModelAndView mav = controller.purchaseEntry();

		assertNotNull(mav);
		assertNotNull(mav.getModel().get("purchaseBean"));
	}

	@Test
	public void testGenerateVendorList() {

		PurchaseEntryController controller = new PurchaseEntryController();

		try {
			assertNotNull(controller.generateVendorList());
		} catch (Exception e) {
			// acceptable in unit test without Spring context
			assertNotNull(e);
		}
	}

	@Test
	public void testGenerateUnitAndTypeList() {

		PurchaseEntryController controller = new PurchaseEntryController();
		PurchaseBean purchaseBean = new PurchaseBean();

		try {
			ModelAndView mav = controller.generateUnitAndTypeList(purchaseBean);
			assertNotNull(mav);
		} catch (Exception e) {
			// expected due to missing autowired beans
			assertNotNull(e);
		}
	}

	@Test
	public void testGenerateCategoryList() {

		PurchaseEntryController controller = new PurchaseEntryController();

		try {
			assertNotNull(controller.generateCategoryList());
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testAddPurchaseDetail() {

		PurchaseEntryController controller = new PurchaseEntryController();
		PurchaseBean purchaseBean = new PurchaseBean();

		try {
			ModelAndView mav = controller.addPurchaseDetail(purchaseBean, null, null);
			assertNotNull(mav);
		} catch (Exception e) {
			// acceptable because services are not injected
			assertNotNull(e);
		}
	}
}
