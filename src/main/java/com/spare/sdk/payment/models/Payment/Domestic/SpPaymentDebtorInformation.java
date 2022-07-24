package com.spare.sdk.payment.models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpPaymentDebtorInformation extends SpModel {
    @JsonProperty("customerReferenceId")
    private String customerReferenceId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phone")
    private String phone;

    public SpPaymentDebtorInformation() {
    }

    public SpPaymentDebtorInformation(String customerReferenceId, String email, String fullName, String phone) {
        this.customerReferenceId = customerReferenceId;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getCustomerReferenceId() {
        return customerReferenceId;
    }

    public void setCustomerReferenceId(String customerReferenceId) {
        this.customerReferenceId = customerReferenceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
