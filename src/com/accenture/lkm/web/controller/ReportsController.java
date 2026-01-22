package com.accenture.lkm.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorWisePurchaseReportBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.ReportsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/IMS/reports")
public class ReportsController {

    private static Logger LOGGER =
            Logger.getLogger(ReportsController.class);

    @Autowired
    private ReportsService reportsService;

    /**
     * Vendor-wise purchase report
     */
    @GetMapping("/vendor")
    public ResponseEntity<List<PurchaseBean>> getPurchaseDetails(
            @RequestBody VendorWisePurchaseReportBean bean)
            throws MicroServiceException {

        LOGGER.info("Fetching purchase report for vendor: "
                + bean.getVendorName());

        List<PurchaseBean> purchaseList =
                reportsService.getVendorWisePurchaseDetails(
                        bean.getFromDate(),
                        bean.getToDate(),
                        bean.getVendorName()
                );

        return ResponseEntity.ok(purchaseList);
    }
}
