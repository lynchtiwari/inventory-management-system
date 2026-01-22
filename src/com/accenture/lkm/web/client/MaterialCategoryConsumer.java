package com.accenture.lkm.web.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.exceptions.MicroServiceException;

@Service
public class MaterialCategoryConsumer {

    private static Logger LOGGER =
            Logger.getLogger(MaterialCategoryConsumer.class);

    @Value("${MaterialServiceConsumer.serviceURL}")
    private String serviceURL;

    @Value("${MaterialCategoryConsumer.apiURL}")
    private String apiURL;

    @Value("${MaterialCategoryConsumer.apiURLForById}")
    private String apiURLForById;

    private RestTemplate restTemplate;

    private List<MaterialCategoryBean> materialCategoryBeanList;
    private Map<String, String> categoryMap;

    public MaterialCategoryConsumer() {
        this.restTemplate = new RestTemplate();
    }

    /* ---------- PUBLIC METHODS ---------- */

    public List<MaterialCategoryBean> getMaterialCategoryBeanList()
            throws MicroServiceException {

        if (materialCategoryBeanList == null) {
            hitGetMaterialCategories();
        }
        return materialCategoryBeanList;
    }

    public Map<String, String> getCategoryMap()
            throws MicroServiceException {

        if (categoryMap == null) {
            hitGetMaterialCategories();
        }
        return categoryMap;
    }

    /**
     * Get category by ID
     */
    public MaterialCategoryBean hitGetMaterialCategoryById(String categoryId)
            throws MicroServiceException {

        try {
            String url =
                    serviceURL + "/" + apiURLForById + "/" + categoryId;

            LOGGER.info("Calling Material Category MS: " + url);

            return restTemplate.getForObject(
                    url, MaterialCategoryBean.class);

        } catch (Exception e) {
            LOGGER.error("Material Category MS call failed", e);
            throw new MicroServiceException();
        }
    }

    /* ---------- PRIVATE MICRO SERVICE CALL ---------- */

    private void hitGetMaterialCategories()
            throws MicroServiceException {

        try {
            String url = serviceURL + "/" + apiURL;

            LOGGER.info("Calling Material Category MS: " + url);

            MaterialCategoryBean[] response =
                    restTemplate.getForObject(
                            url, MaterialCategoryBean[].class);

            materialCategoryBeanList = Arrays.asList(response);
            categoryMap = new HashMap<>();

            for (MaterialCategoryBean b : materialCategoryBeanList) {
                categoryMap.put(
                        b.getCategoryId(),
                        b.getCategoryName());
            }

        } catch (Exception e) {
            LOGGER.error("Material Category MS call failed", e);
            throw new MicroServiceException();
        }
    }
}
