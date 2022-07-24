package com.spare.sdk.payment.client;

import com.spare.sdk.payment.models.payment.domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.response.SpareSdkResponse;

import java.util.ArrayList;

public interface ISpPaymentClient {

    /**
     * Create domestic payment
     */
    SpCreateDomesticPaymentResponse createDomesticPayment(SpDomesticPaymentRequest paymentRequest, String signature) throws Exception;

    /**
     * Get domestic payment
     */
    SpareSdkResponse<SpDomesticPaymentResponse, Object> getDomesticPayment(String id) throws Exception;

    /**
     * List domestic payments
     */
    SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> listDomesticPayments(int start , int perPage) throws  Exception;
}
