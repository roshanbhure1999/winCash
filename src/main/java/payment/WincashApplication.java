package payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class WincashApplication {

    public static void main(String[] args) {
        SpringApplication.run(WincashApplication.class, args);
    }

}
