package com.spare.sdk.payment.models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpUserAccount extends SpAccount {

    @JsonProperty("customerReferenceId")
    private String customerReferenceId;

    @JsonProperty("customerPaymentLink")
    private String customerPaymentLink;

    public String getCustomerReferenceId() {
        return customerReferenceId;
    }

    public String getCustomerPaymentLink() {
        return customerPaymentLink;
    }
}
