package mariel.testsexamples.repository.mongo;

import com.mongodb.assertions.Assertions;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MongoClientTest {

    @InjectMocks
    private MongoClientWrapper mongoClientWrapper;

    @Mock
    private MongoClient client;

    @Test
    // this test just verifies that mongoClientWrapper has a mock of MongoClient
    public void mongoClientWrapperGetCollectionTest(){
        var db = Mockito.mock(MongoDatabase.class);
        when(db.getCollection(anyString())).thenReturn(Mockito.mock(MongoCollection.class));
        when(client.getDatabase(anyString())).thenReturn(db);
        var collection = this.mongoClientWrapper.getCollection("","");
        Assertions.assertNotNull(collection);
    }


}
