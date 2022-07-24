import com.spare.sdk.payment.models.payment.domestic.SpDomesticPayment;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SerialisationTest {

    /**
     * Null
     */
    @Test
    @Order(1)
    void should_not_contain_null() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.setAmount(null);
            payment.setDescription("testing null value");
            String result = payment.toJsonString();
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
    void should_validate_amount_pattern() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.setAmount(229920.5040001000);
            payment.setDescription("testing decimal to string");
            String result = payment.toJsonString();
            assertThat(result).matches(Pattern.compile("(?s).*\"(amount)\":\"((\\\\\"|[^\"])*)\".*$"))
                    .as("Serialized json should comply with the pattern");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
