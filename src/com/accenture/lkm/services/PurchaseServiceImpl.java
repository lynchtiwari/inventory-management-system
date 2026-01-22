package com.accenture.lkm.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PurchaseBean;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static Logger LOGGER = Logger.getLogger(PurchaseServiceImpl.class);

	// Auto wire PurchaseDAO here

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details data into the purchase
	 * table. Also, this method will add a single row into the payment table
	 * with paid amount as zero to keep the track of the balance amount for a
	 * specific purchase.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	@Override
	public PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		return null;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method will be called by addPurchaseDetails method to insert the
	 * data into the Purchase table.
	 * 
	 * @param purchaseBean
	 * @return purchaseBean
	 * @throws Exception
	 */
	private PurchaseBean insertPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		return null;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to generate the transaction id as per logic- P_first
	 * 3 characters of vendor name_purchase date in mmddyyyy format_first 3
	 * characters of material category purchased_primary key of purchase table
	 * 
	 * @param vendorName
	 * @param materialCategoryName
	 * @param purchaseDate
	 * @return String
	 */
	private String transactionIdGenerator(String vendorName, String materialCategoryName, Date purchaseDate) {
		return null;

	}

}
