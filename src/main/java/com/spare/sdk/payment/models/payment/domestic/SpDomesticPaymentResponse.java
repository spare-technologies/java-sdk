package com.spare.sdk.payment.models.payment.domestic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.payment.Enum.SpPaymentSource;
import com.spare.sdk.payment.models.payment.account.SpPaymentIssuer;
import com.spare.sdk.payment.models.payment.account.SpPaymentUserAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpDomesticPaymentResponse extends SpDomesticPayment {
    @JsonProperty("id")
    private String id;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("issuer")
    private SpPaymentIssuer issuer;

    @JsonProperty("issuedFrom")
    private SpPaymentSource issuedFrom;

    @JsonProperty("debtor")
    private SpPaymentUserAccount debtor;

    @JsonProperty("link")
    private String link;

    @JsonProperty("createdAt")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SpPaymentIssuer getIssuer() {
        return issuer;
    }

    public void setIssuer(SpPaymentIssuer issuer) {
        this.issuer = issuer;
    }

    public SpPaymentSource getIssuedFrom() {
        return issuedFrom;
    }

    public void setIssuedFrom(SpPaymentSource issuedFrom) {
        this.issuedFrom = issuedFrom;
    }

    public SpPaymentUserAccount getDebtor() {
        return debtor;
    }

    public void setDebtor(SpPaymentUserAccount debtor) {
        this.debtor = debtor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
