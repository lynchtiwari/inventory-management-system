package com.accenture.lkm.business.bean;

import java.util.Date;

/*
 *Declare fields to set or get from date, to date and vendor name.
  Validate the fields using spring validation annotations.
 *Generate toString method. Add default and parameterized constructors.
 */
public class VendorWisePurchaseReportBean {
	private Date fromDate;

    private Date toDate;

    private String vendorName;

    public VendorWisePurchaseReportBean() {}

    public VendorWisePurchaseReportBean(Date fromDate, Date toDate, String vendorName) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.vendorName = vendorName;
    }

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Override
    public String toString() {
        return "VendorWisePurchaseReportBean [vendorName=" + vendorName + "]";
    }
}
