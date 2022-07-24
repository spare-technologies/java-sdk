package com.spare.sdk.payment.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpareSdkResponse<T, V> extends SpModel {

    @JsonProperty("error")
    private String error;

    @JsonProperty("data")
    private T data;

    @JsonProperty("meta")
    private V meta;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public V getMeta() {
        return meta;
    }

    public void setMeta(V meta) {
        this.meta = meta;
    }
}
