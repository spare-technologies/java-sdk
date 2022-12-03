package com.spare.sdk.payment.enumeration;

public enum SpPaymentStatus {
    AWAITING_AUTHORIZATION("AWAITING_AUTHORIZATION", 0),

    PENDING("PENDING", 1),

    COMPLETED("COMPLETED", 2),

    REJECTED("REJECTED", 3);
    private String name;

    private int value;

    SpPaymentStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
