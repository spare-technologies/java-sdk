import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SerialisationTest {

    /**
     * Null
     */
    @Test
    @Order(1)
    void Should_Not_Contain_Null() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.setAmount(null);
            payment.setDescription("testing null value");
            String result = payment.toJsonString();
            System.out.println(result);
            assertThat(result).doesNotContain("amount")
                    .as("Serialized json should not contain null values");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Amount patten validation
     */
    @Test
    @Order(2)
    void Should_Validate_Amount_Pattern() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.setAmount(229920.5040001000);
            payment.setDescription("testing decimal to string");
            String result = payment.toJsonString();
            System.out.println(result);
            assertThat(result).matches(Pattern.compile("(?s).*\"(amount)\":\"((\\\\\"|[^\"])*)\".*$"))
                    .as("Serialized json should comply with the pattern");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
