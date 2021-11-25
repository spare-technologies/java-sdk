import com.spare.sdk.payment.models.Payment.Domestic.SpDomesticPayment;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SerialisationTest {

    @Test
    @Order(1)
    void Should_Not_Contain_Null() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.Amount = null;
            payment.Description = "testing null value";
            String result = payment.toJsonString();
            System.out.println(result);
            assertThat(result).doesNotContain("amount");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Order(2)
    void Should_Validate_Amount_Pattern() {
        try {
            SpDomesticPayment payment = new SpDomesticPayment();
            payment.Amount = 229920.5040001000;
            payment.Description = "testing decimal to string";
            String result = payment.toJsonString();
            System.out.println(result);
            assertThat(result).matches(Pattern.compile("(?s).*\"(amount)\":\"((\\\\\"|[^\"])*)\".*$"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
