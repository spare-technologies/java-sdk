import com.spare.sdk.payment.client.ISpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClientOptions;
import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import com.spare.sdk.security.dsa.ecdsa.SpEccSignatureManager;
import org.junit.jupiter.api.*;

import java.net.URI;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpPaymentClientTest {

    public static String privateKey = """
            """;
    public static String publicKey = """
            """;

    public static String serverPublicKey = """
            """;

    private ISpPaymentClient paymentClient;

    private static String paymentId;

    public void setPaymentId(String paymentId) {
        SpPaymentClientTest.paymentId = paymentId;
    }


    @BeforeEach
    public void init() {
        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.BaseUrl = URI.create("https://payment.tryspare.com");
        clientOptions.ApiKey = "Your API key";
        clientOptions.AppId = "Your app id";
        this.paymentClient = new SpPaymentClient(clientOptions);
    }

    @Test
    @Order(1)
    void Should_Create_Payment_And_Verify_Signature() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.Amount = 80.06;
            payment.Description = "Shopping";
            SpCreateDomesticPaymentResponse data = this.paymentClient.CreateDomesticPayment(payment, SpEccSignatureManager.Sign(privateKey, payment.toJsonString()));
            setPaymentId(data.Payment.Id);
            assertThat(data.Payment.Link).isNotNull();
            assertThat(data.Signature.isBlank()).isFalse();
            assertThat(SpEccSignatureManager.Verify(serverPublicKey, data.Payment.toJsonString(), data.Signature)).isTrue();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void Should_Get_Payment() {
        try {
            SpDomesticPaymentResponse data = this.paymentClient.GetDomesticPayment(paymentId);
            System.out.println(data);
            assertThat(data.Id).isNotNull();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Order(3)
    void Should_List_Payment() {
        try {
            ArrayList<SpDomesticPaymentResponse> data = this.paymentClient.ListDomesticPayments(0, 10);
            assertThat(data.size()).isGreaterThan(0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
