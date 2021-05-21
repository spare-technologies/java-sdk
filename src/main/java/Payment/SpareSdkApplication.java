package Payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpareSdkApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpareConfiguration.class);
        SpringApplication.run(SpareSdkApplication.class, args);
    }

}
