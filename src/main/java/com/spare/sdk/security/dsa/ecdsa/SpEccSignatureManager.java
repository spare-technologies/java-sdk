package com.spare.sdk.security.dsa.ecdsa;

import com.google.common.io.BaseEncoding;
import com.spare.sdk.utils.SpUtils;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class SpEccSignatureManager {

    private static String PRIVATE_KEY_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static String EC_PRIVATE_KEY_HEADER = "-----BEGIN EC PRIVATE KEY-----";
    private static String EC_PRIVATE_KEY_FOOTER = "-----END EC PRIVATE KEY-----";
    private static String PRIVATE_KEY_FOOTER = "-----END PRIVATE KEY-----";
    private static String PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    private static String PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";

    /**
     * Sign message
     *
     * @param privateKey
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String Sign(String privateKey, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(ReadPrivateKey(privateKey));
        signer.update(data.getBytes(StandardCharsets.UTF_8));
        return SpUtils.BytesToBase64(signer.sign());
    }

    /**
     * Verify signature
     * @param publicKey
     * @param data
     * @param signature
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean Verify(String publicKey, String data, String signature) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initVerify(ReadPublicKey(publicKey));
        signer.update(data.getBytes(StandardCharsets.UTF_8));
        return signer.verify(SpUtils.DecodeFromBase64(signature));
    }

    /**
     * Read EC public key
     * @param pemPublicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static ECPublicKey ReadPublicKey(String pemPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String base64 = pemPublicKey.replace(PUBLIC_KEY_FOOTER, "")
                .replace(PUBLIC_KEY_HEADER, "");
        BaseEncoding encode = BaseEncoding.base64();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(encode.withSeparator("\n", 64).decode(base64));
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return (ECPublicKey) keyFactory.generatePublic(spec);
    }

    /**
     * Read ecc private key from
     *
     * @param pemPrivateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static ECPrivateKey ReadPrivateKey(String pemPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String base64 = pemPrivateKey.replace(EC_PRIVATE_KEY_HEADER, "")
                .replace(EC_PRIVATE_KEY_FOOTER, "")
                .replace(PRIVATE_KEY_HEADER, "")
                .replace(PRIVATE_KEY_FOOTER, "");

        BaseEncoding encode = BaseEncoding.base64();

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(encode.withSeparator("\n", 64).decode(base64));
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        return (ECPrivateKey) keyFactory.generatePrivate(spec);
    }
}
