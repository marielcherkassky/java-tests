package mariel.testsexamples.repository.mongo;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes={MongoProperties.class})
@EnableAutoConfiguration
/*
In order to use the properies injection of spring we must use the @SpringBootTest annotation which loads the context.
In order to minimize the application context, I mentioned what classes to load as beans
 */
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
