# java-sdk
[![Maven Central](https://img.shields.io/maven-central/v/com.tryspare/java.sdk.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.tryspare%22%20AND%20a:%22java.sdk%22)
![Test and analyse workflow](https://github.com/spare-technologies/java-sdk/actions/workflows/dev_build_and_analyse.yml/badge.svg)
![Build and deploy workflow](https://github.com/spare-technologies/java-sdk/actions/workflows/master_build_and_deploy.yml/badge.svg)
### Usage

#### I- Add dependencies to ```pom.xml``` file

```xml
 <dependency>
    <groupId>com.tryspare</groupId>
    <artifactId>java.sdk</artifactId>
    <version>1.1.0</version>
 </dependency>
``` 

#### II- To Generate ECC key pair

```java
import com.spare.sdk.security.crypto.SpCrypto;

public class MyClass {
    SpEcKeyPair eccKeyPair = SpCrypto.generateKeyPair();
    
    System.out.println("Private key \n", eccKeyPair.getPrivateKey());
    System.out.println("\n\n");
    System.out.println("Private key \n", eccKeyPair.getPublicKey());
}
```

#### III- To create your first payment request

```java
import com.spare.sdk.payment.models.builder.SpDomesticPaymentRequestBuilder;
import com.spare.sdk.payment.models.payment.domestic.SpDomesticPaymentRequest;

public class MyClass {

    // Business ECC private key
    public static String privateKey = """""";

    // Spare ECC public key
    public static String serverPublicKey = """""";

    public static void main(String[] args) {

        // Configure client
        SpPaymentClientOptions clientOptions = new SpPaymentClientOptions();
        clientOptions.setBaseUrl(URI.create("https://payment.tryspare.com"));
        clientOptions.setApiKey("Your API key");
        clientOptions.setAppId("Your app id");

        SpPaymentClient client = new SpPaymentClient(clientOptions);

        // Initialize payment
        SpDomesticPaymentRequestBuilder paymentRequestBuilder = new SpDomesticPaymentRequestBuilder();
        SpDomesticPaymentRequest paymentRequest = paymentRequestBuilder.setAmount(10.0)
                .setDescription("Shopping")
                .setOrderId("12345")
                .build();

        // Sign the payment 
        String signature = SpEccSignatureManager.Sign(privateKey, paymentRequest.toJsonString());

        // Create payment

        SpCreateDomesticPaymentResponse paymentResponse = client.CreateDomesticPayment(paymentRequest, signature);

        // To verify signature of the created payment 
        if (SpEccSignatureManager.Verify(serverPublicKey, paymentResponse.getPayment().toJsonString(), createPayment.getSignature())) {
            // signature verified
        }
    }
}
```