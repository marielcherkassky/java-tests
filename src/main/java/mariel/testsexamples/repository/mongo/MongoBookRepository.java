package mariel.testsexamples.repository.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mariel.testsexamples.metrics.MetricsManager;
import mariel.testsexamples.models.Book;
import mariel.testsexamples.repository.BookRepository;
import mariel.testsexamples.utils.Mapping;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static com.mongodb.client.model.Filters.eq;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class MongoBookRepository implements BookRepository {
    private final MongoClientWrapper mongoClientWrapper;
    private final String collection;
    private final String db;
    private final ObjectMapper objectMapper;
    private final MetricsManager metricsManager;

    public MongoBookRepository(
            @Autowired MongoClientWrapper wrapper,
            @Value("${db.mongo.tables.books}") String collection,
            @Value("${db.mongo.db}") String db,
            @Autowired MetricsManager metricsManager
    ){
        this.mongoClientWrapper = wrapper;
        this.collection = collection;
        this.db = db;
        this.metricsManager = metricsManager;
        this.objectMapper = new ObjectMapper();;
    }


    @Override
    public String saveBook(Book book) {
        this.metricsManager.increment("saveBook");
        var doc = Mapping.convertObjectTo(objectMapper,book,Map.class);
        if(doc == null) return null;
        var result = this.mongoClientWrapper.getCollection(db,collection).insertOne(new Document(doc)).getInsertedId();
        if (result == null) return null;
        return book.getId();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        this.metricsManager.increment("getBookById");
        var collection = this.mongoClientWrapper.getCollection(db,this.collection);
        var result = collection.find(eq("id",id)).projection(new BsonDocument("_id", new BsonInt32(0))).first();
        if(result == null) return Optional.empty();
        return Optional.ofNullable(Mapping.convertObjectTo(objectMapper,result,Book.class));
    }



}
