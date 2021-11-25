import com.spare.sdk.security.dsa.ecdsa.SpEcdsa;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EcdsaTest {
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

    @Test
    public void Should_Sign_And_Verify() {
        var data = "data";
        try {
            var signature = SpEcdsa.Sign(privateKey, data);
            assertThat(signature).isNotBlank();
            var verify = SpEcdsa.Verify(publicKey, data, signature);
            assertThat(verify).isTrue();

            var falseVerify = SpEcdsa.Verify(publicKey,"data2",signature);
            assertThat(falseVerify).isFalse();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
