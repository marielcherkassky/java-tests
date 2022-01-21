package mariel.testsexamples.repository.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("db.mongo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MongoProperties {
    private String host;
    private String db;
    private String user;
    private String password;
}
