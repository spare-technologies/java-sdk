package com.spare.sdk.payment.models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpDomesticPaymentRequest extends SpDomesticPayment {
    @JsonProperty("customerInformation")
    private SpPaymentDebtorInformation customerInformation;

    public SpDomesticPaymentRequest() {
    }

    public SpDomesticPaymentRequest(SpPaymentDebtorInformation customerInformation) {
        this.customerInformation = customerInformation;
    }

    public SpDomesticPaymentRequest(Double amount, String description, SpPaymentDebtorInformation customerInformation) {
        super(amount, description);
        this.customerInformation = customerInformation;
    }

    public SpDomesticPaymentRequest(Double amount, String description, String orderId, SpPaymentDebtorInformation customerInformation) {
        super(amount, description, orderId);
        this.customerInformation = customerInformation;
    }

    public SpPaymentDebtorInformation getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(SpPaymentDebtorInformation customerInformation) {
        this.customerInformation = customerInformation;
    }
}
