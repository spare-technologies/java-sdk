import Payment.Client.ISpPaymentClient;
import Payment.Client.SpPaymentClient;
import Payment.Client.SpPaymentClientOptions;
import Payment.Models.Payment.Domestic.SpDomesticPayment;
import Payment.Models.Payment.Domestic.SpDomesticPaymentResponse;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpPaymentClientTest {
    private  ISpPaymentClient paymentClient;
    private static UUID paymentId;

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }




    @BeforeEach
    public void init() {
        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.BaseUrl = URI.create("https://dev.tryspare.com");
        clientOptions.ApiKey =  "deVF3jjcOggbtFJWiRN0M246lBpADD5MVvaowKJlFfg=";
        clientOptions.AppId = "mHzLLIyaKyClbr5WPP8v3mqu1PLHfRqEJfaNkqXt/Og=";
        this.paymentClient = new SpPaymentClient(clientOptions);
    }

    @Test
    @Order(1)
    void CreatePayment() throws Exception {
        SpDomesticPayment payment = new SpDomesticPayment();
        payment.Amount = BigDecimal.valueOf(80);
        payment.Description = "Shopping";
        SpDomesticPaymentResponse data = this.paymentClient.CreateDomesticPayment(payment);
        setPaymentId(data.Id);
        System.out.println(this.paymentId);
        assertThat(data.Link).isNotNull();
    }

    @Test
    @Order(2)
    void GetPayment() throws Exception {
        SpDomesticPaymentResponse data = this.paymentClient.GetDomesticPayment(getPaymentId().toString());
        assertThat(data.Id).isNotNull();
    }
    @Test
    @Order(3)
    void ListPayment() throws Exception {
        ArrayList<SpDomesticPaymentResponse> data = this.paymentClient.ListDomesticPayments(0, 10);
        assertThat(data.size() ).isGreaterThan(0);
    }
}
