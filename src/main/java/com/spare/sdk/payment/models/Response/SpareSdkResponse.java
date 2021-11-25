package com.spare.sdk.payment.models.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpareSdkResponse<T, TV> extends SpModel {

    @JsonProperty("error")
    public String Error;

    @JsonProperty("data")
    public T Data;

    @JsonProperty("meta")
    public TV Meta;
}
