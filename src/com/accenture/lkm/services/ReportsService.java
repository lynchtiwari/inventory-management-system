package com.accenture.lkm.services;

import java.util.Date;
import java.util.List;

import com.accenture.lkm.business.bean.PurchaseBean;

import java.text.ParseException;

public interface ReportsService {
	public List<PurchaseBean> getVendorWisePurchaseDetails(Date from, Date to, String vendorName);
}
