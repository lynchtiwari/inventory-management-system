package com.accenture.lkm.web.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class UnitServiceConsumer {

    private static Logger LOGGER =
            Logger.getLogger(UnitServiceConsumer.class);

    @Value("${MaterialServiceConsumer.serviceURL}")
    private String serviceURL;

    @Value("${UnitServiceConsumer.apiURL}")
    private String apiURL;

    @Value("${UnitServiceConsumer.apiURLByCategoryId}")
    private String apiURLByCategoryId;

    private List<UnitBean> unitBeanList;
    private Map<String, String> unitMap;

    private RestTemplate restTemplate;

    public UnitServiceConsumer() {
        this.restTemplate = new RestTemplate();
    }

    /* ---------- PUBLIC METHODS ---------- */

    public List<UnitBean> getUnitBeanList()
            throws MicroServiceException {

        if (unitBeanList == null) {
            hitGetUnitDetails();
        }
        return unitBeanList;
    }

    public Map<String, String> getUnitMap()
            throws MicroServiceException {

        if (unitMap == null) {
            hitGetUnitDetails();
        }
        return unitMap;
    }

    /**
     * Units by category (used by REST controller)
     */
    public List<UnitBean> hitGetUnitsByCategoryId(String categoryId)
            throws MicroServiceException {

        try {
            String url =
                    serviceURL + "/" + apiURLByCategoryId + "/" + categoryId;

            LOGGER.info("Calling Unit MS: " + url);

            UnitBean[] response =
                    restTemplate.getForObject(url, UnitBean[].class);

            return Arrays.asList(response);

        } catch (Exception e) {
            LOGGER.error("Unit MS call failed", e);
            throw new MicroServiceException();
        }
    }

    /* ---------- PRIVATE MICRO SERVICE CALL ---------- */

    private void hitGetUnitDetails()
            throws MicroServiceException {

        try {
            String url = serviceURL + "/" + apiURL;

            LOGGER.info("Calling Unit MS: " + url);

            UnitBean[] response =
                    restTemplate.getForObject(url, UnitBean[].class);

            unitBeanList = Arrays.asList(response);
            unitMap = new HashMap<>();

            for (UnitBean b : unitBeanList) {
                unitMap.put(b.getUnitId(), b.getUnitName());
            }

        } catch (Exception e) {
            LOGGER.error("Unit MS call failed", e);
            throw new MicroServiceException();
        }
    }
}
