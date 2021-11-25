package com.spare.sdk.payment.Enum;

public enum SpPaymentSource {
    MOBILE("MOBILE", 0),
    WEB("WEB", 1),
    API("API", 2);

    private String name;
    private int value;

    private SpPaymentSource(String name, int value){
        this.name = name;
        this.value = value;
    }
}
