package Payment.Models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public  class SpPaymentUserAccount {

    @JsonProperty("account")
    public SpUserAccount Account;

    @JsonProperty("bankAccount")
    public SpUserPaymentBankAccount BankAccount;
}
