package com.accenture.lkm.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorWisePurchaseReportBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.ReportsService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for handling the request coming from
 * DateWisePurchaseReport.jsp
 *
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ReportsController {

	private static Logger LOGGER = Logger.getLogger(ReportsController.class);

	@Autowired
	private ReportsService reportsService;

	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;

	@Autowired
	private UnitServiceConsumer unitServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;
	
	List <PurchaseBean> purchaseIdList;

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the purchase details for a given vendor and
	 * between the two given dates 
	 *  
	 * @param vendorWisePurchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(
			value = "report/controller/getPurchaseDetails",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PurchaseBean>> getPurchaseDetails(
			@RequestBody VendorWisePurchaseReportBean bean) throws MicroServiceException {

		LOGGER.info("Fetching purchase report for vendor: " + bean.getVendorName());

		List<PurchaseBean> purchaseList =
				reportsService.getVendorWisePurchaseDetails(
						bean.getFromDate(),
						bean.getToDate(),
						bean.getVendorName()
				);

		return ResponseEntity.ok(purchaseList);
	}

		
	
}