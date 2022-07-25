import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.spare.sdk.payment.Enum.SpPaymentSource;
import com.spare.sdk.payment.client.ISpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClient;
import com.spare.sdk.payment.client.SpPaymentClientOptions;
import com.spare.sdk.payment.client.SpProxy;
import com.spare.sdk.payment.models.payment.domestic.SpCreateDomesticPaymentResponse;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentResponse;
import com.spare.sdk.payment.models.payment.domestic.SpPaymentDebtorInformation;
import com.spare.sdk.payment.models.response.SpareSdkResponse;
import com.spare.sdk.payment.models.builder.SpDomesticPaymentRequestBuilder;
import com.spare.sdk.security.dsa.ecdsa.SpEccSignatureManager;
import models.SpTestEnvironment;
import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.io.IOUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpPaymentClientTest {

    private ISpPaymentClient paymentClient;

    private static String paymentId;

    private SpTestEnvironment testEnvironment;

    public void setPaymentId(String paymentId) {
        SpPaymentClientTest.paymentId = paymentId;
    }


    @BeforeEach
    public void init() {
        this.LoadTestEnvironment();

        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.setBaseUrl(URI.create(testEnvironment.getBaseUrl()));
        clientOptions.setApiKey(testEnvironment.getApiKey());
        clientOptions.setAppId(testEnvironment.getAppId());

        if (testEnvironment.getProxy() != null) {
            clientOptions.setProxy(new SpProxy());

            clientOptions.getProxy().setHost(new HttpHost(testEnvironment.getProxy().getHost(), Integer.parseInt(testEnvironment.getProxy().getPort())));

            clientOptions.getProxy().setCredentials(new UsernamePasswordCredentials(testEnvironment.getProxy().getUsername(), testEnvironment.getProxy().getPassword()));
        }

        this.paymentClient = new SpPaymentClient(clientOptions);
    }

    /**
     * Create domestic payment test
     */
    @Test
    @Order(1)
    void should_create_payment_and_verify_signature() {
        try {
            Faker faker = Faker.instance();

            SpDomesticPaymentRequestBuilder paymentRequestBuilder = new SpDomesticPaymentRequestBuilder();
            SpDomesticPaymentRequest paymentRequest = paymentRequestBuilder
                    .setAmount(Math.abs(Double.parseDouble(faker.commerce().price())))
                    .setDescription(faker.commerce().productName())
                    .setOrderId(faker.random().nextInt(1000, 20000).toString())
                    .build();

            SpCreateDomesticPaymentResponse paymentResponse = this.paymentClient.createDomesticPayment(paymentRequest,
                    SpEccSignatureManager.sign(testEnvironment.getEcKeypair().getPrivate(), paymentRequest.toJsonString()));

            assertThat(paymentResponse).isNotNull().as("Payment response should not be null");

            if (testEnvironment.getDebugMode()) {
                System.out.println(paymentResponse.toJsonString());
            }

            assertThat(paymentResponse.getPayment().getAmount()).isEqualTo(paymentRequest.getAmount())
                    .as("Payment response amount should be equal to payment request amount");

            assertThat(paymentResponse.getPayment().getLink()).isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response link should not be null");

            assertThat(SpEccSignatureManager.verify(testEnvironment.getServerPublicKey(),
                    paymentResponse.getPayment().toJsonString(), paymentResponse.getSignature()))
                    .isTrue()
                    .as("Payment response signature should be valid");

            setPaymentId(paymentResponse.getPayment().getId());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Create domestic payment with customer information test
     */
    @Test
    @Order(2)
    void should_create_payment_with_customer_information() {
        try {
            Faker faker = Faker.instance();

            SpDomesticPaymentRequestBuilder paymentRequestBuilder = new SpDomesticPaymentRequestBuilder();
            SpDomesticPaymentRequest paymentRequest = paymentRequestBuilder
                    .setAmount(Math.abs(Double.parseDouble(faker.commerce().price())))
                    .setDescription(faker.commerce().productName())
                    .setOrderId(faker.random().nextInt(1000, 20000).toString())
                    .setCustomerInformation(new SpPaymentDebtorInformation(
                            faker.random().nextInt(2000, 100000).toString(),
                            "email@example.com",
                            faker.name().fullName(),
                            faker.phoneNumber().cellPhone().replace("-", "")
                                    .replace(".", "")
                                    .replace("(", "")
                                    .replace(")", "")
                                    .replace(" ", "")))
                    .build();

            SpCreateDomesticPaymentResponse paymentResponse = this.paymentClient.createDomesticPayment(paymentRequest,
                    SpEccSignatureManager.sign(testEnvironment.getEcKeypair().getPrivate(), paymentRequest.toJsonString()));

            assertThat(paymentResponse).isNotNull().as("Payment response should not be null");

            if (testEnvironment.getDebugMode()) {
                System.out.println(paymentResponse.toJsonString());
            }

            assertThat(SpEccSignatureManager.verify(testEnvironment.getServerPublicKey(),
                    paymentResponse.getPayment().toJsonString(), paymentResponse.getSignature()))
                    .isTrue()
                    .as("Payment response signature should be valid");

            assertThat(paymentResponse.getPayment().getId())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response id should not be null");

            assertThat(paymentResponse.getPayment().getAmount()).isEqualTo(paymentRequest.getAmount())
                    .as("Payment response amount should be equal to payment request amount");

            assertThat(paymentResponse.getPayment().getIssuedFrom()).isEqualTo(SpPaymentSource.API)
                    .as("Payment should be issued from API");

            assertThat(paymentResponse.getPayment().getCurrency())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment currency should not be null");

            assertThat(paymentResponse.getPayment().getReference())
                    .isNotNull()
                    .isNotBlank()
                    .isNotEmpty()
                    .as("Payment reference should not be null");

            assertThat(paymentResponse.getPayment().getCreatedAt())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment created at should not be null");

            assertThat(paymentResponse.getPayment().getDescription())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response description should not be null");

            assertThat(paymentResponse.getPayment().getOrderId())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response order id should not be null");

            assertThat(paymentResponse.getPayment().getLink())
                    .isNotNull()
                    .isNotBlank()
                    .isNotEmpty()
                    .as("Payment response link should not be null");

            assertThat(paymentResponse.getPayment().getDebtor())
                    .isNotNull()
                    .as("Payment response debtor should not be null");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount())
                    .isNotNull()
                    .as("Payment response debtor account should not be null");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount().getId())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response debtor id should not be nul");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount().getFullName())
                    .isNotNull()
                    .isNotBlank()
                    .isNotEmpty()
                    .as("Payment response debtor fullname should not be null");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount().getEmail())
                    .isNotNull()
                    .isNotBlank()
                    .isNotEmpty()
                    .as("Payment response debtor email should not be null");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount().getPhone())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment response debtor phone should not be null");

            assertThat(paymentResponse.getPayment().getDebtor().getAccount().getCustomerReferenceId())
                    .isNotNull()
                    .isNotBlank()
                    .isNotEmpty()
                    .as("Payment response debtor customer reference should not be null");

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    /**
     * Get domestic payment test
     */
    @Test
    @Order(3)
    void should_get_payment() {
        try {
            SpareSdkResponse<SpDomesticPaymentResponse, Object> paymentResponse = this.paymentClient.getDomesticPayment(paymentId);

            assertThat(paymentResponse).isNotNull().as("Payment response should not be null");

            assertThat(paymentResponse.getError()).isNull();

            assertThat(paymentResponse.getData()).isNotNull()
                    .as("Payment data should not be null");

            assertThat(paymentResponse.getData().getReference())
                    .isNotNull()
                    .isNotEmpty()
                    .isNotBlank()
                    .as("Payment reference should not be null");

            if (testEnvironment.getDebugMode()) {
                System.out.println(paymentResponse.toJsonString());
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * List domestic payments test
     */
    @Test
    @Order(4)
    void Should_List_Payment() {
        try {
            Faker faker = Faker.instance();

            SpareSdkResponse<ArrayList<SpDomesticPaymentResponse>, Object> listDomesticPayments = this.paymentClient.listDomesticPayments(0,
                    faker.random().nextInt(1, 500));

            assertThat(listDomesticPayments).isNotNull().as("Domestic payments list response should not be null");

            assertThat(listDomesticPayments.getError()).isNull();

            assertThat(listDomesticPayments.getData()).isNotNull()
                    .as("Domestic payments list response data should not be null");

            if (!listDomesticPayments.getData().isEmpty()) {
                listDomesticPayments.getData().forEach(spDomesticPaymentResponse -> {
                    assertThat(spDomesticPaymentResponse).isNotNull()
                            .as("Payment should not be null");

                    assertThat(spDomesticPaymentResponse.getReference())
                            .isNotNull()
                            .as("Payment should have a reference");
                });
            }

            if (testEnvironment.getDebugMode()) {
                System.out.println(listDomesticPayments.toJsonString());
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Missing payment request signature test
     */
    @Test
    @Order(5)
    void should_through_exception_on_missing_signature() {
        Faker faker = Faker.instance();

        SpDomesticPaymentRequestBuilder paymentRequestBuilder = new SpDomesticPaymentRequestBuilder();
        SpDomesticPaymentRequest paymentRequest = paymentRequestBuilder
                .setAmount(Math.abs(Double.parseDouble(faker.commerce().price())))
                .setDescription(faker.commerce().productName())
                .setOrderId(faker.random().nextInt(1000, 20000).toString())
                .build();


        assertThrows(Exception.class, () -> this.paymentClient.createDomesticPayment(paymentRequest, ""));
    }

    /**
     * Wrong sdk configuration test
     */
    @Test
    @Order(6)
    void should_validate_client_configuration() {
        assertThrows(Exception.class, () -> paymentClient = new SpPaymentClient(null));
    }

    /**
     * Missing api key test
     */
    @Test
    @Order(7)
    void should_check_missing_api_key() {
        assertThrows(Exception.class, () -> {
            SpPaymentClientOptions spPaymentClientOptions = new SpPaymentClientOptions();
            spPaymentClientOptions.setApiKey(null);
            spPaymentClientOptions.setAppId(testEnvironment.getAppId());
            spPaymentClientOptions.setBaseUrl(new URI(testEnvironment.getBaseUrl()));

            paymentClient = new SpPaymentClient(spPaymentClientOptions);
        });
    }

    /**
     * Missing app id test
     */
    @Test
    @Order(8)
    void should_check_missing_app_id() {
        assertThrows(Exception.class, () -> {
            SpPaymentClientOptions spPaymentClientOptions = new SpPaymentClientOptions();
            spPaymentClientOptions.setApiKey(testEnvironment.getApiKey());
            spPaymentClientOptions.setAppId(null);
            spPaymentClientOptions.setBaseUrl(new URI(testEnvironment.getBaseUrl()));

            paymentClient = new SpPaymentClient(spPaymentClientOptions);
        });
    }

    private void LoadTestEnvironment() {
        try {
            String jsonTestEnvironment = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("testEnvironment.json")), StandardCharsets.UTF_8);
            this.testEnvironment = new ObjectMapper().readValue(jsonTestEnvironment, SpTestEnvironment.class);

            assertThat(this.testEnvironment).isNotNull().as("Test environment should not be null");

            assertThat(this.testEnvironment.getEcKeypair()).isNotNull().as("Test environment key pair should not be null");

            assertThat(this.testEnvironment.getEcKeypair().getPrivate()).isNotNull().isNotBlank().isNotEmpty().as("Test environment key pair private key should be defined");

            assertThat(this.testEnvironment.getEcKeypair().getPrivate()).isNotNull().isNotBlank().isNotEmpty().as("Test environment key pair public key should be defined");

            assertThat(this.testEnvironment.getApiKey()).isNotNull().isNotBlank().isNotEmpty().as("Test environment api key should be defined");

            assertThat(this.testEnvironment.getAppId()).isNotNull().isNotBlank().isNotEmpty().as("Test environment app id should be defined");

            assertThat(this.testEnvironment.getBaseUrl()).isNotNull().isNotBlank().isNotEmpty().as("Test environment base url should be defined");

            if (testEnvironment.getProxy() == null) {
                return;
            }

            assertThat(this.testEnvironment.getProxy().getHost()).isNotNull().isNotBlank().isNotEmpty().as("Test environment base proxy host should be defined");

            assertThat(this.testEnvironment.getProxy().getPort()).isNotNull().isNotBlank().isNotEmpty().as("Test environment base proxy port should be defined");

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
