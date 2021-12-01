# java-sdk

### Usage

#### I- Add dependencies to ```pom.xml``` file

```xml
 <dependency>
    <groupId>com.tryspare</groupId>
    <artifactId>java.sdk</artifactId>
    <version>1.0.0</version>
</dependency>
``` 

#### II- To Generate ECC key pair

```java
import com.spare.sdk.security.crypto.SpCrypto;

public class MyClass {
    var eccKeyPair = SpCrypto.GenerateKeyPair();
    System.out.println("Private key \n",eccKeyPair.PrivateKey);
    System.out.println("\n\n");
    System.out.println("Private key \n",eccKeyPair.PublicKey);
}
```

#### III- To create your first payment request

```java
public class MyClass {
    
     // Business ECC private key
     public static String privateKey = """""";
     
     // Spare ECC public key
     public static String serverPublicKey = """""";
 
     public static void main(String[] args) {
 
        // Configure client
        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.BaseUrl = URI.create("https://payment.tryspare.com");
        clientOptions.ApiKey = "Your API key";
        clientOptions.AppId = "Your app id";
    
        SpPaymentClient client = new SpPaymentClient(clientOptions);
    
    
        // Initialize payment
        SpDomesticPayment payment = new SpDomesticPayment();
        payment.Amount = 80.06;
        payment.Description = "Shopping";

        // Sign the payment 
        var signature = SpEccSignatureManager.Sign(privateKey, payment.toJsonString());

        // Create payment

        SpCreateDomesticPaymentResponse createPayment = client.CreateDomesticPayment(payment,signature);

        // To verify signature of the created payment 
        if(SpEccSignatureManager.Verify(serverPublicKey, createPayment.Payment.toJsonString(), createPayment.Signature)){
            // signature verified
        }
    }
}
```