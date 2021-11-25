package com.spare.sdk.payment.client;

import java.net.URI;

public class SpPaymentClientOptions {

    public URI BaseUrl;

    public String AppId;

    public String ApiKey;

    /**
     * Validate client configuration
     */
    public void ValidateConfiguration(){
        if(BaseUrl == null){
            throw new IllegalArgumentException("Base url is required");
        }

        if(AppId.isBlank() || ApiKey.isBlank()){
            throw new IllegalArgumentException("App id and Api key are required");
        }
    }
}
