package com.accenture.lkm.web.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class MaterialTypeConsumer {

    private static Logger LOGGER =
            Logger.getLogger(MaterialTypeConsumer.class);

    @Value("${MaterialServiceConsumer.serviceURL}")
    private String serviceURL;

    @Value("${MaterialTypeConsumer.apiURL}")
    private String apiURL;

    @Value("${MaterialTypeConsumer.apiURLByCategoryId}")
    private String apiURLByCategoryId;

    private RestTemplate restTemplate;

    private List<MaterialTypeBean> materialTypeBeanList;
    private Map<String, String> categoryTypeMap;

    public MaterialTypeConsumer() {
        this.restTemplate = new RestTemplate();
    }

    /* ---------- PUBLIC METHODS ---------- */

    public List<MaterialTypeBean> getMaterialTypeBeanList()
            throws MicroServiceException {

        if (materialTypeBeanList == null) {
            hitGetMaterialType();
        }
        return materialTypeBeanList;
    }

    public Map<String, String> getCategoryTypeMap()
            throws MicroServiceException {

        if (categoryTypeMap == null) {
            hitGetMaterialType();
        }
        return categoryTypeMap;
    }

    /**
     * Types by category (used by REST controller)
     */
    public List<MaterialTypeBean> hitGetTypesBasedOnCategoryId(String categoryId)
            throws MicroServiceException {

        try {
            String url =
                    serviceURL + "/" + apiURLByCategoryId + "/" + categoryId;

            LOGGER.info("Calling Material Type MS: " + url);

            MaterialTypeBean[] response =
                    restTemplate.getForObject(url, MaterialTypeBean[].class);

            return Arrays.asList(response);

        } catch (Exception e) {
            LOGGER.error("Material Type MS call failed", e);
            throw new MicroServiceException();
        }
    }

    /* ---------- PRIVATE MICRO SERVICE CALL ---------- */

    private void hitGetMaterialType()
            throws MicroServiceException {

        try {
            String url = serviceURL + "/" + apiURL;

            LOGGER.info("Calling Material Type MS: " + url);

            MaterialTypeBean[] response =
                    restTemplate.getForObject(url, MaterialTypeBean[].class);

            materialTypeBeanList = Arrays.asList(response);
            categoryTypeMap = new HashMap<>();

            for (MaterialTypeBean b : materialTypeBeanList) {
                categoryTypeMap.put(b.getTypeId(), b.getTypeName());
            }

        } catch (Exception e) {
            LOGGER.error("Material Type MS call failed", e);
            throw new MicroServiceException();
        }
    }
}
