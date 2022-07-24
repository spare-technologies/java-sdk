package com.spare.sdk.payment.models.payment.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpUserPaymentBankAccount extends SpModel {
    @JsonProperty("scheme")
    private String scheme;

    @JsonProperty("identification")
    private String identification;

    public String getScheme() {
        return scheme;
    }

    public String getIdentification() {
        return identification;
    }
}
