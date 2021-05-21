package Payment.Client;

import Payment.Models.Payment.Domestic.SpDomesticPayment;
import Payment.Models.Payment.Domestic.SpDomesticPaymentResponse;
import java.util.ArrayList;

public interface ISpPaymentClient {

    /**
     * Create domestic payment
     * @param payment
     * @return
     */
    SpDomesticPaymentResponse CreateDomesticPayment(SpDomesticPayment payment) throws Exception;

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
