package com.accenture.lkm.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

@Controller
@CrossOrigin(origins="http://localhost:3000")
public class PurchaseEntryController {

	private static Logger LOGGER = Logger.getLogger(PurchaseEntryController.class);

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;

	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;

	@Autowired
	private UnitServiceConsumer unitServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	/**
	 * Load Purchase Entry Page
	 */
	@RequestMapping(value = "/api/IMS/purchaseentry/all", method = RequestMethod.GET)
	public ModelAndView purchaseEntry() {

		LOGGER.info("Loading Purchase Entry Page");

		ModelAndView mav = new ModelAndView("PurchaseEntry");
		mav.addObject("purchaseBean", new PurchaseBean());

		return mav;
	}

	/**
	 * Vendor dropdown
	 */
	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {
		return vendorServiceConsumer.getVendorBeanList();
	}

	/**
	 * Category dropdown
	 */
	@ModelAttribute("categoryList")
	public List<MaterialCategoryBean> generateCategoryList() throws MicroServiceException {
		return materialCategoryConsumer.getMaterialCategoryBeanList();
	}

	/**
	 * Load Unit & Type based on Category
	 */
	@RequestMapping(value = "/api/IMS/getUnitAndTypeList", method = RequestMethod.POST)
	public ModelAndView generateUnitAndTypeList(
			@ModelAttribute("purchaseBean") PurchaseBean purchaseBean)
			throws MicroServiceException {

		LOGGER.info("Fetching Units & Types");

		ModelAndView mav = new ModelAndView("PurchaseEntry");

		mav.addObject("purchaseBean", purchaseBean);
		mav.addObject("unitList",
				unitServiceConsumer.hitGetUnitsByCategoryId(purchaseBean.getMaterialCategoryId()));
		mav.addObject("typeList",
				materialTypeConsumer.hitGetTypesBasedOnCategoryId(purchaseBean.getMaterialCategoryId()));

		return mav;
	}

	/**
	 * Submit Purchase
	 */
	@RequestMapping(value = "/api/IMS/addPurchaseDetail/create", method = RequestMethod.POST)
	public ModelAndView addPurchaseDetail(
			@ModelAttribute("purchaseBean") @Valid PurchaseBean purchaseBean,
			BindingResult result,
			ModelMap map) throws Exception {

		LOGGER.info("Submitting Purchase");

		if (result.hasErrors()) {
			LOGGER.error("Validation errors");
			return new ModelAndView("PurchaseEntry");
		}

		PurchaseBean savedBean = purchaseService.addPurchaseDetails(purchaseBean);

		ModelAndView mav = new ModelAndView("PurchaseSuccess");
		mav.addObject("purchaseBean", savedBean);

		return mav;
	}
}
