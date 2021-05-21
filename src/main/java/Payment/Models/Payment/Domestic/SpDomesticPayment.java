package Payment.Models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.net.URI;

public class SpDomesticPayment {

    @JsonProperty("amount")
    public BigDecimal Amount;

    @JsonProperty("description")
    public String Description;

    @JsonProperty("successUrl")
    public URI SuccessUrl;

    @JsonProperty("failUrl")
    public URI FailUrl;



}
