# java-sdk

### Usage

1- Add dependencies to pom file

```xml
 <dependency>
    <groupId>com.spare</groupId>
    <artifactId>sdk</artifactId>
    <version>1.0.0</version>
 </dependency>
``` 

2- Create a your first payment request

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