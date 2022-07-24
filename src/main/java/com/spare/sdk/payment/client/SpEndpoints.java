package com.spare.sdk.payment.client;

public final class SpEndpoints {

    private String value;

    public String getValue() {
        return value;
    }

    private SpEndpoints(String value) {
        this.value = value;
    }

    public static final SpEndpoints createDomesticPayments = new SpEndpoints("/api/v1.0/payments/domestic/Create");
    public static final SpEndpoints getDomesticPayment = new SpEndpoints("/api/v1.0/payments/domestic/Get");
    public static final SpEndpoints listDomesticPayments = new SpEndpoints("/api/v1.0/payments/domestic/List");
}
