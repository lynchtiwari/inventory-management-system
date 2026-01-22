package com.accenture.lkm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/IMS")
public class PurchaseEntryController {

    private static Logger LOGGER =
            Logger.getLogger(PurchaseEntryController.class);

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

    /* ---------------- VENDORS ---------------- */

    @GetMapping("/vendors")
    public List<VendorBean> getVendors()
            throws MicroServiceException {
        return vendorServiceConsumer.getVendorBeanList();
    }

    /* ---------------- CATEGORIES ---------------- */

    @GetMapping("/categories")
    public List<MaterialCategoryBean> getCategories()
            throws MicroServiceException {
        return materialCategoryConsumer.getMaterialCategoryBeanList();
    }

    /* ---------------- UNITS & TYPES ---------------- */

    @GetMapping("/units-types/{categoryId}")
    public Map<String, Object> getUnitsAndTypes(
            @PathVariable String categoryId)
            throws MicroServiceException {

        Map<String, Object> response = new HashMap<>();

        List<UnitBean> units =
                unitServiceConsumer.hitGetUnitsByCategoryId(categoryId);

        List<MaterialTypeBean> types =
                materialTypeConsumer.hitGetTypesBasedOnCategoryId(categoryId);

        response.put("units", units);
        response.put("types", types);

        return response;
    }

    /* ---------------- CREATE PURCHASE ---------------- */

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseBean> createPurchase(
            @RequestBody @Valid PurchaseBean purchaseBean)
            throws Exception {

        LOGGER.info("Creating purchase via REST");

        PurchaseBean saved =
                purchaseService.addPurchaseDetails(purchaseBean);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
