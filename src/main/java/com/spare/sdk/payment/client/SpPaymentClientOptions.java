package com.spare.sdk.payment.client;

import java.net.URI;

public class SpPaymentClientOptions {

    private URI baseUrl;

    private String appId;

    private String apiKey;

    private SpProxy proxy;

    public URI getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(URI baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public SpProxy getProxy() {
        return proxy;
    }

    public void setProxy(SpProxy proxy) {
        this.proxy = proxy;
    }

    /**
     * Validate client configuration
     */
    public void validateConfiguration(){
        if(baseUrl == null){
            throw new IllegalArgumentException("Base url is required");
        }

        if(appId == null || appId.equals("") || apiKey == null || apiKey.equals("")){
            throw new IllegalArgumentException("App id and Api key are required");
        }
    }
}
