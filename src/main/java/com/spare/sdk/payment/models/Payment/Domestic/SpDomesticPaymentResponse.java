package com.spare.sdk.payment.models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.payment.Enum.SpPaymentSource;
import com.spare.sdk.payment.models.Payment.Account.SpPaymentUserAccount;
import com.spare.sdk.payment.models.Payment.Account.SpUserAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpDomesticPaymentResponse extends SpDomesticPayment {
    @JsonProperty("id")
    public String Id;

    @JsonProperty("reference")
    public String reference;

    @JsonProperty("currency")
    public String Currency;

    @JsonProperty("issuer")
    public SpUserAccount Issuer;

    @JsonProperty("issuedFrom")
    public SpPaymentSource IssuedFrom;

    @JsonProperty("debtor")
    public SpPaymentUserAccount Debtor;

    @JsonProperty("link")
    public String Link;

    @JsonProperty("createdAt")
    public String CreatedAt;

}
