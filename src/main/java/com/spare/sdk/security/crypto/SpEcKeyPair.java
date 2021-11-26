package com.spare.sdk.security.crypto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SpEcKeyPair {

    public SpEcKeyPair(String privateKey, String publicKey) {
        this.PrivateKey = privateKey;
        this.PublicKey = publicKey;
    }

    public SpEcKeyPair() {}

    @JsonProperty("publicKey")
    public String PublicKey;

    @JsonProperty("privateKey")
    public String PrivateKey;
}
