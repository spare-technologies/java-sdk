package com.spare.sdk.payment.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;

public class SpProxy {
    private HttpHost host;

    private UsernamePasswordCredentials credentials;

    public SpProxy() {
    }

    public SpProxy(HttpHost host) {
        this.host = host;
    }

    public SpProxy(HttpHost host, UsernamePasswordCredentials credentials) {
        this.host = host;
        this.credentials = credentials;
    }

    public HttpHost getHost() {
        return host;
    }

    public void setHost(HttpHost host) {
        this.host = host;
    }

    public UsernamePasswordCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UsernamePasswordCredentials credentials) {
        this.credentials = credentials;
    }
}
