package org.ff4j.store;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2017 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.swagger.annotations.Api;
import org.ff4j.core.Feature;
import org.ff4j.exception.FeatureNotFoundException;
import org.ff4j.store.client.ApiClient;
import org.ff4j.store.client.ApiException;
import org.ff4j.store.client.ApiResponse;
import org.ff4j.store.client.api.FeatureResourceApi;
import org.ff4j.store.client.api.FeatureStoreResourceApi;
import org.ff4j.store.client.model.FeatureApiBean;
import org.ff4j.store.client.model.InlineResponse2003;
import org.ff4j.store.client.model.InlineResponse2004;
import org.ff4j.utils.Util;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peterdietz on 7/25/17.
 */
public class RESTFeatureStore extends AbstractFeatureStore {
    private FeatureResourceApi featureClient = null;
    private FeatureStoreResourceApi featureStoreResourceApi = null;


    public RESTFeatureStore(URL apiURL) {
        ApiClient client = new ApiClient();
        client.setBasePath(apiURL.toString());
        featureClient = new FeatureResourceApi(client);
        featureStoreResourceApi = new FeatureStoreResourceApi(client);
    }

    @Override
    public boolean exist(String featId) {
        assertHasLength(featId);
        try {
            List<String> features = featureStoreResourceApi.getFeatureStoreUsingGET().getFeatures();
            return features.contains(featId);
        } catch (ApiException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void create(Feature fp) {
        FeatureApiBean featureApiBean = new FeatureApiBean();
        featureApiBean.setUid(fp.getUid());
        featureApiBean.setDescription(fp.getDescription());
        featureApiBean.setEnable(fp.isEnable());
        try {
            featureClient.createOrUpdateFeatureUsingPUT(fp.getUid(), featureApiBean);
        } catch (ApiException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Feature read(String featureUid) {
        assertFeatureExist(featureUid);
        try {
            InlineResponse2004 response = featureClient.getFeatureByUIDUsingGET(featureUid);
            Feature feature = new Feature(response.getUid(), response.getEnable(), response.getDescription(), response.getGroup());
            return feature;
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Map<String, Feature> readAll() {
        try {
            List<String> features = featureStoreResourceApi.getFeatureStoreUsingGET().getFeatures();
            Map<String, Feature> featureMap = new HashMap<String, Feature>();
            for(String featureName : features){
                featureMap.put(featureName, read(featureName));
            }

            return featureMap;
        } catch (ApiException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String fpId) {
        assertFeatureExist(fpId);
        try {
            featureClient.deleteFeatureUsingDELETE(fpId);
        } catch (ApiException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Feature fp) {
        create(fp);
    }

    @Override
    public void clear() {
        try {
            featureStoreResourceApi.clearCachedFeatureStoreUsingDELETE();
        } catch (ApiException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Validate feature uid.
     *
     * @param uid
     *      target uid
     */
    protected void assertFeatureExist(String uid) {
        Util.assertHasLength(uid);
        if (!exist(uid)) {
            throw new FeatureNotFoundException(uid);
        }
    }

    /**
     * Check that string is not null
     *
     * @param object
     *            target object
     */
    public static void assertHasLength(String... params) {
        if (params == null) {
            throw new IllegalArgumentException("[Assertion failed] - Parameter #0 (string)  must not be null nor empty");
        }
        if (params != null) {
            for (int idx = 0; idx < params.length ;idx++) {
                String currentparam = params[idx];
                if (null == currentparam || currentparam.isEmpty()) {
                    throw new IllegalArgumentException("[Assertion failed] - Parameter #" + idx + "(string)  must not be null nor empty");
                }
            }
        }
    }
}