package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpTestEnvironment {

    @JsonProperty("debugMode")
    private boolean debugMode;

    @JsonProperty("baseUrl")
    private String baseUrl;

    @JsonProperty("ecKeypair")
    SpTestEcKeypair ecKeypair;

    @JsonProperty("serverPublicKey")
    private String serverPublicKey;

    @JsonProperty("apiKey")
    private String apiKey;

    @JsonProperty("appId")
    private String appId;

    @JsonProperty("proxy")
    SpTestProxy proxy;

    public boolean getDebugMode() {
        return debugMode;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public SpTestEcKeypair getEcKeypair() {
        return ecKeypair;
    }

    public String getServerPublicKey() {
        return serverPublicKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getAppId() {
        return appId;
    }

    public SpTestProxy getProxy() {
        return proxy;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setEcKeypair(SpTestEcKeypair ecKeypair) {
        this.ecKeypair = ecKeypair;
    }

    public void setServerPublicKey(String serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setProxy(SpTestProxy proxyObject) {
        this.proxy = proxyObject;
    }
}