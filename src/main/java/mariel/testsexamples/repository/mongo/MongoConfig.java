package mariel.testsexamples.repository.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Slf4j
public class MongoConfig {

    @Autowired
    private MongoProperties properties;

    @Bean
    @Profile("!test")
    MongoClient getMongoClient(){
        var credentials = MongoCredential.createCredential(
                properties.getUser(),properties.getDb(),properties.getPassword().toCharArray());

        var clusterSettings = ClusterSettings.builder()
                .hosts(List.of(new ServerAddress(properties.getHost())))
                .build();

        var settings =  MongoClientSettings.builder()
                .credential(credentials)
                .applyToClusterSettings(block -> block.applySettings(clusterSettings))
                .build();
        log.info("Creating mongo client for prod..");

        return MongoClients.create(settings);
    }
}
