package com.spare.sdk.payment.client;

import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import java.util.ArrayList;

public interface ISpPaymentClient {

    /**
     * Create domestic payment
     * @param payment
     * @return
     */
    SpCreateDomesticPaymentResponse CreateDomesticPayment(SpDomesticPayment payment, String signature) throws Exception;

    /**
     * Get domestic payment
      * @param id
     * @return
     */
    SpDomesticPaymentResponse GetDomesticPayment(String id) throws Exception;

    /**
     * List domestic payments
     * @param start
     * @param perPage
     * @return
     */
    ArrayList<SpDomesticPaymentResponse> ListDomesticPayments(int start , int perPage) throws  Exception;


}
