package com.spare.sdk.payment.client;

import com.spare.sdk.payment.SpClientSdkException;
import com.spare.sdk.payment.models.payment.domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.response.SpareSdkResponse;

import java.io.IOException;
import java.util.ArrayList;

public interface ISpPaymentClient {

    /**
     * Create domestic payment
     */
    SpCreateDomesticPaymentResponse createDomesticPayment(SpDomesticPaymentRequest paymentRequest, String signature) throws IOException, SpClientSdkException;

    /**
     * Get domestic payment
     */
    SpareSdkResponse<SpDomesticPaymentResponse, Object> getDomesticPayment(String id) throws IOException, SpClientSdkException;

    /**
     * List domestic payments
     */
    SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> listDomesticPayments(int start, int perPage) throws IOException, SpClientSdkException;
}
