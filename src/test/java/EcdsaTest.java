import com.spare.sdk.security.dsa.ecdsa.SpEccSignatureManager;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EcdsaTest {
    public static String privateKey ="-----BEGIN PRIVATE KEY-----\n" +
            "            MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgWMr9j+0+68xr6iwc\n" +
            "            rP02+M9jfH0StJIEWcbcouKA8K2hRANCAATseSJA81lLCYWwC4hQKWa8J2mOlbfN\n" +
            "            Jxo1SqP46W5Yb1kgcmKsnit5YZUT0WnyDvX3KXlYXnf1OwIltzn97vEc\n" +
            "            -----END PRIVATE KEY-----";

    public static String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "            MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE7HkiQPNZSwmFsAuIUClmvCdpjpW3\n" +
            "            zScaNUqj+OluWG9ZIHJirJ4reWGVE9Fp8g719yl5WF539TsCJbc5/e7xHA==\n" +
            "            -----END PUBLIC KEY-----";

    @Test
    public void Should_Sign_And_Verify() {
        String data = "data";
        try {
            String signature = SpEccSignatureManager.Sign(privateKey, data);
            assertThat(signature).isNotBlank();
            boolean verify = SpEccSignatureManager.Verify(publicKey, data, signature);
            assertThat(verify).isTrue();

            boolean falseVerify = SpEccSignatureManager.Verify(publicKey,"data2",signature);
            assertThat(falseVerify).isFalse();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
