package com.accenture.lkm.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for ReportsDAO which deals with all the reports related
 * queries<br/>
 *
 */
@Repository
public class ReportsDAOImpl implements ReportsDAO {
	private static Logger LOGGER = Logger.getLogger(ReportsDAOImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*
	 * This method fetches purchase details for a given vendor name. If to and from
	 * date is given then it fetches data between the given dates.
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
	public List<PurchaseBean> getVendorPurchaseReport(Date from, Date to, String vendorName) {
		return null;
	}
}
