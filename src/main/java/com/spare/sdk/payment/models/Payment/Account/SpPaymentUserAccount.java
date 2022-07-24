package com.spare.sdk.payment.models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpPaymentUserAccount extends SpModel {

    @JsonProperty("account")
    private SpUserAccount account;

    @JsonProperty("bankAccount")
    private SpUserPaymentBankAccount bankAccount;

    public SpUserAccount getAccount() {
        return account;
    }

    public SpUserPaymentBankAccount getBankAccount() {
        return bankAccount;
    }
}
