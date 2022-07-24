package com.spare.sdk.payment.client;

import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.Response.SpareSdkResponse;

import java.util.ArrayList;

public interface ISpPaymentClient {

    /**
     * Create domestic payment
     */
    SpCreateDomesticPaymentResponse CreateDomesticPayment(SpDomesticPaymentRequest paymentRequest, String signature) throws Exception;

    /**
     * Get domestic payment
     */
    SpareSdkResponse<SpDomesticPaymentResponse, Object> GetDomesticPayment(String id) throws Exception;

    /**
     * List domestic payments
     */
    SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> ListDomesticPayments(int start , int perPage) throws  Exception;
}
