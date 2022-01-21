package mariel.testsexamples.repository.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoClientWrapper {

    private MongoClient client;

    public MongoClientWrapper(@Autowired MongoClient client){
        this.client = client;
    }

    public  MongoCollection<Document> getCollection(String db, String collection){
        return this.client.getDatabase(db).getCollection(collection);
    }







}
