package Payment.Models.Payment.Domestic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.net.URI;

public class SpDomesticPayment {

    @JsonProperty("amount")
    public Double Amount;

    @JsonProperty("description")
    public String Description;

}
