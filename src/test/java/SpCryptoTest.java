import com.spare.sdk.security.crypto.SpCrypto;
import com.spare.sdk.security.crypto.SpEcKeyPair;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
public class SpCryptoTest {

    @Test
    public void Should_generate_key_pair(){
        try{
            SpEcKeyPair keys = SpCrypto.GenerateKeyPair();
            assertThat(keys).isNotNull();
            assertThat(keys.PrivateKey).isNotBlank();
            assertThat(keys.PublicKey).isNotBlank();
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
