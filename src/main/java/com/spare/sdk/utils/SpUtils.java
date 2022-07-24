package com.spare.sdk.utils;


import com.google.crypto.tink.subtle.Base64;

public final class SpUtils {

    private SpUtils() {
    }

    /**
     * Encode to base64
     */
    public static String bytesToBase64(byte[] data) {
        return Base64.encodeToString(data, Base64.NO_WRAP);
    }

    /**
     * Decode from base64
     */
    public static byte[] decodeFromBase64(String base64){
        return Base64.decode(base64,Base64.NO_WRAP);
    }
}
