package com.spare.sdk.payment.client;

public final class SpEndpoints {

    public String Value;

    private SpEndpoints(String value)
    {
        Value = value;
    }

    public static SpEndpoints CreateDomesticPayments = new SpEndpoints("/api/v1.0/payments/domestic/Create");
    public static SpEndpoints GetDomesticPayment = new SpEndpoints("/api/v1.0/payments/domestic/Get");
    public static SpEndpoints ListDomesticPayments = new SpEndpoints("/api/v1.0/payments/domestic/List");
}
