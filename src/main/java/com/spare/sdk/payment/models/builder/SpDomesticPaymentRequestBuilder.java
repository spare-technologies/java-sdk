package com.spare.sdk.payment.models.builder;

import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.payment.domestic.SpPaymentDebtorInformation;

public class SpDomesticPaymentRequestBuilder {

    private final SpDomesticPaymentRequest paymentRequest;

    public SpDomesticPaymentRequestBuilder() {
        paymentRequest = new SpDomesticPaymentRequest();
    }

    /**
     * Set payment request amount
     */
    public SpDomesticPaymentRequestBuilder setAmount(Double amount) {
        this.paymentRequest.setAmount(amount);
        return this;
    }

    /**
     * Set payment request description
     */
    public SpDomesticPaymentRequestBuilder setDescription(String description) {
        this.paymentRequest.setDescription(description);
        return this;
    }

    /**
     * Set payment request order id
     */
    public SpDomesticPaymentRequestBuilder setOrderId(String orderId) {
        this.paymentRequest.setOrderId(orderId);
        return this;
    }

    /**
     * Set payment request customer information
     */
    public SpDomesticPaymentRequestBuilder setCustomerInformation(SpPaymentDebtorInformation customerInformation) {
        this.paymentRequest.setCustomerInformation(customerInformation);
        return this;
    }

    /**
     * Create payment request
     */
    public SpDomesticPaymentRequest build() {
        return this.paymentRequest;
    }
}
