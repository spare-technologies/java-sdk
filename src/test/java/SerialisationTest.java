import Payment.Client.SerializerConfiguration;
import Payment.Models.Payment.Domestic.SpDomesticPayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SerialisationTest
{
    ObjectMapper mapper;
    @BeforeEach
    public void init()
    {
      mapper = SerializerConfiguration.SetConfiguration();

    }

    @Test
    @Order(1)
    void TestNullValues() throws JsonProcessingException {
        SpDomesticPayment payment = new SpDomesticPayment();
        payment.Amount = null;
        payment.Description = "testing null value";
        String result = mapper.writeValueAsString(payment);
        System.out.println(result);
        assertThat(result).doesNotContain("amount");

    }

    @Test
    @Order(2)
    void TestDecimalToStringValues() throws JsonProcessingException
    {
        SpDomesticPayment payment = new SpDomesticPayment();
        payment.Amount = new BigDecimal(2);
        payment.Description = "testing decimal to string";
        String result = mapper.writeValueAsString(payment);
        System.out.println(result);
        Pattern p =  Pattern.compile("(?s).*\"(amount)\":\"((\\\\\"|[^\"])*)\".*$");
        assertThat(result).matches(p);
    }
}
