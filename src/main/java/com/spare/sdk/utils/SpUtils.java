package com.spare.sdk.utils;


import com.google.crypto.tink.subtle.Base64;

public final class SpUtils {
    /**
     * Encode to base64
     */
    public static String BytesToBase64(byte[] data) {
        return Base64.encodeToString(data, Base64.NO_WRAP);
    }

    /**
     * Decode from base64
     */
    public static byte[] DecodeFromBase64(String base64){
        return Base64.decode(base64,Base64.NO_WRAP);
    }
}
