package com.moveosoftware.infrastructure.mvvm.model.network;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by oferdan-on on 8/26/17
 */

public class ApiConfig {


    private String baseUrl;

    private String version;

    private String authHeaderKey;
    private String authHeaderValue;

    private List<Interceptor> interceptors;

    private ApiConfig(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthHeaderKey() {
        return authHeaderKey;
    }

    public void setAuthHeaderKey(String authHeaderKey) {
        this.authHeaderKey = authHeaderKey;
    }

    public String getAuthHeaderValue() {
        return authHeaderValue;
    }

    public void setAuthHeaderValue(String authHeaderValue) {
        this.authHeaderValue = authHeaderValue;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static class Builder {

        private String baseUrl;

        private String version;

        private String authHeaderKey;
        private String authHeaderValue;
        private List<Interceptor> interceptors = new ArrayList<>();


        public Builder(String baseUrl) {
            this.baseUrl = baseUrl;
        }


        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setAuthHeader(String key, String value) {
            this.authHeaderKey = key;
            this.authHeaderValue = value;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public ApiConfig build() {
            ApiConfig apiConfig = new ApiConfig(baseUrl);

            if (version != null) {
                apiConfig.setVersion(version);
            }
            if (authHeaderKey != null) {
                apiConfig.setAuthHeaderKey(authHeaderKey);
                apiConfig.setAuthHeaderValue(authHeaderValue);
            }

            apiConfig.setInterceptors(interceptors);

            return apiConfig;
        }

    }
}
