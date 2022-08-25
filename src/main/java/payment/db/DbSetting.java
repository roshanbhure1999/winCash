package payment.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
@Data
public class DbSetting {
    private String connection;
    private String host;
    private int port;
}
