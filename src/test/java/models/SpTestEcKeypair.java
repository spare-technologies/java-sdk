package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpTestEcKeypair {
    @JsonProperty("private")
    private String privateKey;

    @JsonProperty("public")
    private String publicKey;


    // Getter Methods

    public String getPrivate() {
        return privateKey;
    }

    public String getPublic() {
        return publicKey;
    }

    // Setter Methods

    public void setPrivate(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublic(String publicKey) {
        this.publicKey = publicKey;
    }
}
