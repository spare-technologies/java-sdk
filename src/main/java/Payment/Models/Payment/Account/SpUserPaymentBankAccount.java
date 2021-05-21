package Payment.Models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpUserPaymentBankAccount {

    @JsonProperty("scheme")
    public String Scheme;

    @JsonProperty("identification")
    public String Identification;

}
