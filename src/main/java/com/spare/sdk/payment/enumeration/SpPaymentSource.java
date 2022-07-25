package com.spare.sdk.payment.enumeration;

public enum SpPaymentSource {
    MOBILE("MOBILE", 0),
    WEB("WEB", 1),
    API("API", 2);

    private String name;
    private int value;

    SpPaymentSource(String name, int value){
        this.name = name;
        this.value = value;
    }
}
