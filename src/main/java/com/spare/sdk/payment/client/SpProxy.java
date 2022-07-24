package com.spare.sdk.payment.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;

public class SpProxy {
    private HttpHost Host;

    private UsernamePasswordCredentials Credentials;

    public SpProxy() {
    }

    public SpProxy(HttpHost host) {
        Host = host;
    }

    public SpProxy(HttpHost host, UsernamePasswordCredentials credentials) {
        Host = host;
        Credentials = credentials;
    }

    public HttpHost getHost() {
        return Host;
    }

    public void setHost(HttpHost host) {
        Host = host;
    }

    public UsernamePasswordCredentials getCredentials() {
        return Credentials;
    }

    public void setCredentials(UsernamePasswordCredentials credentials) {
        Credentials = credentials;
    }
}
