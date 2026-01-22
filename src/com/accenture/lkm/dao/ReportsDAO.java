package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.business.bean.PurchaseBean;



public interface ReportsDAO {
	public List<PurchaseBean> getVendorPurchaseReport(Date from, Date to, String vendorName);
}
