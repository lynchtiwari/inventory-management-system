package com.accenture.lkm.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.ReportsDAO;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for ReportsService to deal with all reports related
 * detail.
 *
 */
@Service
public class ReportsServiceImpl implements ReportsService {

	private static Logger LOGGER = Logger.getLogger(ReportsServiceImpl.class);

	@Autowired
	private ReportsDAO reportsDAO;
	List<PurchaseEntity> purchaseEntity;
	
	/*
	 * This method calls the ReportsDAO class getVendorPurchaseReport method to
	 * fetches purchase details for a given vendor name. If to and from date is
	 * given then it filter the data between the given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param vendorName
	 * 
	 * @return List<PurchaseEntity>
	 */
	@Override
	public List<PurchaseBean> getVendorWisePurchaseDetails(Date from, Date to, String vendorName) {
		return reportsDAO.getVendorPurchaseReport(from, to, vendorName);
	}
}


