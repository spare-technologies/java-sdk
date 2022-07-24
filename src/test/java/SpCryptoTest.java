import com.github.javafaker.Faker;
import com.spare.sdk.security.crypto.SpCrypto;
import com.spare.sdk.security.crypto.SpEcKeyPair;
import com.spare.sdk.security.dsa.ecdsa.SpEccSignatureManager;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpCryptoTest {

    /**
     * Generate EC key pair test
     */
    @Test
    @Order(1)
    public void Should_generate_key_pair() {
        try {
            SpEcKeyPair keys = SpCrypto.generateKeyPair();

            assertThat(keys).isNotNull().as("Keypair should not be null");

            assertThat(keys.getPrivateKey()).isNotBlank().as("Keypair should have private key");

            assertThat(keys.getPublicKey()).isNotBlank().as("Keypair should have public key");

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Sign and verify object test
     */
    @Test
    @Order(2)
    public void Should_Sign_And_Verify() throws Exception {
        Faker faker = Faker.instance();
        String data = faker.lorem().characters();

        SpEcKeyPair keys = SpCrypto.generateKeyPair();

        try {
            String signature = SpEccSignatureManager.sign(keys.getPrivateKey(), data);
            assertThat(signature).isNotBlank().as("Signature should not be blanc");
            boolean verify = SpEccSignatureManager.verify(keys.getPublicKey(), data, signature);
            assertThat(verify).isTrue().as("Signature should be valid");

            boolean falseVerify = SpEccSignatureManager.verify(keys.getPublicKey(), faker.lorem().characters(), signature);
            assertThat(falseVerify).isFalse().as("Signature should not be valid");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
