import com.github.javafaker.Faker;
import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPaymentRequest;
import com.spare.sdk.payment.models.Payment.Domestic.SpPaymentDebtorInformation;
import com.spare.sdk.payment.models.builder.SpDomesticPaymentRequestBuilder;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SpBuildersTest {

    /**
     * Domestic payment request builder test
     */
    @Test
    public void Should_build_domestic_payment_request() {
        SpDomesticPaymentRequestBuilder builder = new SpDomesticPaymentRequestBuilder();

        Faker faker = Faker.instance();

        SpDomesticPaymentRequest paymentRequest = builder
                .setAmount(Double.valueOf(faker.commerce().price()))
                .setDescription(faker.commerce().productName())
                .setOrderId(faker.random().nextInt(1000, 20000).toString())
                .setCustomerInformation(new SpPaymentDebtorInformation(
                        faker.random().nextInt(2000, 100000).toString(),
                        "email@example.com",
                        faker.name().fullName(),
                        faker.phoneNumber().phoneNumber()))
                .build();

        assertThat(paymentRequest).isNotNull()
                .as("Domestic payment request builder should create a payment request");

        assertThat(paymentRequest.getDescription()).isNotBlank()
                .as("Domestic payment request builder should set description");

        assertThat(paymentRequest.getAmount()).isNotNull()
                .as("Domestic payment request builder should set amount");

        assertThat(paymentRequest.getOrderId()).isNotBlank()
                .as("Domestic payment request builder should set order id");

        assertThat(paymentRequest.getCustomerInformation())
                .as("Domestic payment request builder should set customer information");

        assertThat(paymentRequest.getCustomerInformation().getCustomerReferenceId()).isNotBlank()
                .as("Domestic payment request builder should set customer information reference id");

        assertThat(paymentRequest.getCustomerInformation().getEmail()).isNotBlank()
                .as("Domestic payment request builder should set customer information email");

        assertThat(paymentRequest.getCustomerInformation().getFullName()).isNotBlank()
                .as("Domestic payment request builder should set customer information full name");

        assertThat(paymentRequest.getCustomerInformation().getPhone()).isNotBlank()
                .as("Domestic payment request builder should set customer information phone");

    }
}
