package com.spare.sdk.payment.models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spare.sdk.utils.helpers.models.SpModel;
import com.spare.sdk.utils.serialization.DoubleSerializer;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpDomesticPayment extends SpModel {

    @JsonProperty("amount")
    @JsonSerialize(using = DoubleSerializer.class)
    public Double Amount;

    @JsonProperty("description")
    public String Description;

}
