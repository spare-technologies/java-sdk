import com.spare.sdk.payment.client.ISpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClientOptions;
import com.spare.sdk.payment.models.Payment.Domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentResponse;
import com.spare.sdk.security.dsa.ecdsa.SpEcdsa;
import org.junit.jupiter.api.*;

import java.net.URI;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpPaymentClientTest {

    public static String privateKey = """
            -----BEGIN PRIVATE KEY-----
            MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgWMr9j+0+68xr6iwc
            rP02+M9jfH0StJIEWcbcouKA8K2hRANCAATseSJA81lLCYWwC4hQKWa8J2mOlbfN
            Jxo1SqP46W5Yb1kgcmKsnit5YZUT0WnyDvX3KXlYXnf1OwIltzn97vEc
            -----END PRIVATE KEY-----""";
    public static String publicKey = """
            -----BEGIN PUBLIC KEY-----
            MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE7HkiQPNZSwmFsAuIUClmvCdpjpW3
            zScaNUqj+OluWG9ZIHJirJ4reWGVE9Fp8g719yl5WF539TsCJbc5/e7xHA==
            -----END PUBLIC KEY-----""";

    public static String serverPublicKey = """
            -----BEGIN PUBLIC KEY-----
            MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEZergpIl9cU89g/iV97ZLPSyPc7S3
            Z5l3yXTuHXDTOnFwhHr/Pep8UFOl26Gbjxf0I84MjJFsqNsmUSfjdZTr7Q==
            -----END PUBLIC KEY-----""";

    private ISpPaymentClient paymentClient;

    private static String paymentId;

    public void setPaymentId(String paymentId) {
        SpPaymentClientTest.paymentId = paymentId;
    }


    @BeforeEach
    public void init() {
        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.BaseUrl = URI.create("https://devpayment.tryspare.com");
        clientOptions.ApiKey = "jWfuuYv8WKtl/iH0lT24/rVE2LSgG992tV7+tVK2XaA=";
        clientOptions.AppId = "IVol/o5oPkoMMu3JcX9QDn+iN9Cqv1GqTjhTdvtpdfQ=";
        this.paymentClient = new SpPaymentClient(clientOptions);
    }

    @Test
    @Order(1)
    void Should_Create_Payment_And_Verify_Signature() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.Amount = 80.06;
            payment.Description = "Shopping";
            SpCreateDomesticPaymentResponse data = this.paymentClient.CreateDomesticPayment(payment, SpEcdsa.Sign(privateKey, payment.toJsonString()));
            setPaymentId(data.Payment.Id);
            assertThat(data.Payment.Link).isNotNull();
            assertThat(data.Signature.isBlank()).isFalse();
            System.out.println(data.Payment.toJsonString());
            assertThat(SpEcdsa.Verify(serverPublicKey, data.Payment.toJsonString(), data.Signature)).isTrue();
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
