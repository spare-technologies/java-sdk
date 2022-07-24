package com.spare.sdk.security.crypto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SpEcKeyPair {

    @JsonProperty("publicKey")
    public String publicKey;

    @JsonProperty("privateKey")
    public String privateKey;

    public SpEcKeyPair() {}

    public SpEcKeyPair(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
