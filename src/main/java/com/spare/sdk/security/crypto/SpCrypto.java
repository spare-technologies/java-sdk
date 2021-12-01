package com.spare.sdk.security.crypto;

import com.google.common.io.BaseEncoding;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;

public final class SpCrypto {

    private final static String EC_PRIVATE_KEY_HEADER = "-----BEGIN EC PRIVATE KEY-----";
    private final static String EC_PRIVATE_KEY_FOOTER = "-----END EC PRIVATE KEY-----";
    private final static String PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    private final static String PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";

    /**
     * Generate key ecc prime256v1 key pair
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static SpEcKeyPair GenerateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("EC");
        gen.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair keys = gen.generateKeyPair();
        ECPrivateKey pr = (ECPrivateKey) keys.getPrivate();
        ECPublicKey pb = (ECPublicKey) keys.getPublic();

        return new SpEcKeyPair(FormatKey(pr.getEncoded(), true), FormatKey(pb.getEncoded(), false));
    }

    /**
     * Format key
     * @param content
     * @param isPrivate
     * @return
     */
    private static String FormatKey(byte[] content, boolean isPrivate) {
        StringBuilder pem = new StringBuilder();
        BaseEncoding encoder = BaseEncoding.base64();
        if (isPrivate) {
            pem.append(String.format("%s\n", EC_PRIVATE_KEY_HEADER));
            pem.append(
                    encoder.withSeparator("\n", 64)
                            .encode(content)
            );
            pem.append("\n");
            pem.append(EC_PRIVATE_KEY_FOOTER);
        } else {
            pem.append(String.format("%s\n", PUBLIC_KEY_HEADER));
            pem.append(
                    encoder.withSeparator("\n", 64)
                            .encode(content)
            );
            pem.append("\n");
            pem.append(PUBLIC_KEY_FOOTER);
        }
        return pem.toString();
    }
}
