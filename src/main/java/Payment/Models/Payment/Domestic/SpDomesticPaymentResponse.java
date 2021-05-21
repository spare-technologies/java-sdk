package Payment.Models.Payment.Domestic;

import Payment.Enum.Payment.SpPaymentSource;
import Payment.Models.Payment.Account.SpPaymentUserAccount;
import Payment.Models.Payment.Account.SpUserAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SpDomesticPaymentResponse extends SpDomesticPayment {
    @JsonProperty("id")
    public UUID Id;

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
