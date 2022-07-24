package com.spare.sdk.payment.models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpCreateDomesticPaymentResponse extends SpModel {
    @JsonProperty("payment")
    private SpDomesticPaymentResponse payment;

    @JsonProperty("signature")
    private String signature;

    public SpDomesticPaymentResponse getPayment() {
        return payment;
    }

    public void setPayment(SpDomesticPaymentResponse payment) {
        this.payment = payment;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
