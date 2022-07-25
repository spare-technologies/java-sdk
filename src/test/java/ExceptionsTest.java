import com.spare.sdk.payment.SpClientSdkException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionsTest {
    /**
     * Sdk exception test
     */
    @Test
    void should_throw_client_sdk_exception(){
        assertThrows(Exception.class, () -> {
            throw new SpClientSdkException();
        });
    }
}
