package Payment.Models.Payment.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpUserAccount {

    @JsonProperty("id")
    public String Id;

    @JsonProperty("identifier")
    public String Identifier;

    @JsonProperty("name")
    public String Name;

    @JsonProperty("picture")
    public String Picture;

}
