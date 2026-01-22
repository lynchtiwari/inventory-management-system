package com.accenture.lkm.web.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class VendorServiceConsumer {

    private static Logger LOGGER =
            Logger.getLogger(VendorServiceConsumer.class);

    @Value("${VendorServiceConsumer.serviceURL}")
    private String serviceURL;

    @Value("${VendorServiceConsumer.apiURL}")
    private String apiURL;

    private List<VendorBean> vendorBeanList;
    private Map<String, VendorBean> vendorMap;

    private RestTemplate restTemplate;

    public VendorServiceConsumer() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Public method used by REST controllers
     */
    public List<VendorBean> getVendorBeanList()
            throws MicroServiceException {

        if (vendorBeanList == null) {
            hitGetVendorDetails();
        }
        return vendorBeanList;
    }

    public Map<String, VendorBean> getVendorMap()
            throws MicroServiceException {

        if (vendorMap == null) {
            hitGetVendorDetails();
        }
        return vendorMap;
    }

    /**
     * Calls Vendor microservice
     */
    private void hitGetVendorDetails()
            throws MicroServiceException {

        try {
            String url = serviceURL + "/" + apiURL;

            LOGGER.info("Calling Vendor MS: " + url);

            VendorBean[] response =
                    restTemplate.getForObject(
                            url, VendorBean[].class);

            vendorBeanList = Arrays.asList(response);
            vendorMap = new HashMap<>();

            for (VendorBean v : vendorBeanList) {
                vendorMap.put(v.getVendorId(), v);
            }

        } catch (Exception e) {
            LOGGER.error("Vendor MS call failed", e);
            throw new MicroServiceException();
        }
    }
}
