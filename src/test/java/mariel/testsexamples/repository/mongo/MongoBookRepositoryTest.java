package mariel.testsexamples.repository.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import mariel.testsexamples.models.Book;
import mariel.testsexamples.models.Genre;
import mariel.testsexamples.utils.Mapping;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MongoBookRepositoryTest {

    @Autowired
    private MongoBookRepository mongoBookRepository;

    @InjectMocks
    private MongoClientWrapper mongoClientWrapper;

    @MockBean
    private MongoClient mongoClient;

    @Test
    public void saveBookTest(){
        var db = Mockito.mock(MongoDatabase.class, Mockito.RETURNS_DEEP_STUBS);
        when(mongoClient.getDatabase(anyString())).thenReturn(db);
        Book book = new Book("id","name","author", Set.of(Genre.ACTION));
        String result = this.mongoBookRepository.saveBook(book);
        Assertions.assertEquals(book.getId(),result);
    }

    @Test
    public void getBookByIdTest(){
        var db = Mockito.mock(MongoDatabase.class, Mockito.RETURNS_DEEP_STUBS);
        when(mongoClient.getDatabase(anyString())).thenReturn(db);
        Book book = new Book("id","name","author", Set.of(Genre.ACTION));
        Document doc = Mapping.convertObjectTo(new ObjectMapper(),book, Document.class);
        when(db.getCollection(anyString()).find(any(Bson.class)).projection(any()).first()).thenReturn(doc);
        var result = this.mongoBookRepository.getBookById(book.getId());
        Assertions.assertEquals(result.get().getId(),book.getId());
    }

}
