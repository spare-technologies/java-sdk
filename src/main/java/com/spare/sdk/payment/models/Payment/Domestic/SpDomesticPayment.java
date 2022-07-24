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
    private Double amount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("orderId")
    private String orderId;

    public SpDomesticPayment() {
    }

    public SpDomesticPayment(Double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public SpDomesticPayment(Double amount, String description, String orderId) {
        this.amount = amount;
        this.description = description;
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
