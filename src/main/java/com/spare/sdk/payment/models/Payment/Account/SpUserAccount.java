package com.spare.sdk.payment.models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spare.sdk.utils.helpers.models.SpModel;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SpUserAccount extends SpModel {

    @JsonProperty("id")
    public String Id;

    @JsonProperty("identifier")
    public String Identifier;

    @JsonProperty("name")
    public String Name;

    @JsonProperty("picture")
    public String Picture;

}
