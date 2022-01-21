package mariel.testsexamples.repository.mongo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties({MongoProperties.class})
public class MongoPropertiesTest {

    @Autowired
    private  MongoProperties properties;

    @Test
    public void mongoPropertiesLoadedTest(){
        assertNotNull(properties.getDb());
        assertNotNull(properties.getHost());
        assertNotNull(properties.getPassword());
        assertNotNull(properties.getUser());
    }
}
