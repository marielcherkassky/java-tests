package mariel.testsexamples.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import mariel.testsexamples.models.Book;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MappingUnitTest {

    @Test
    public void mappingUnitTest(){
        var mapper = new ObjectMapper();
        var bookMap = Map.of(
                "name","just-random-book-name",
                "author","oda",
                "id","1",
                "genre", Arrays.asList("action")
        );
        var book = Mapping.convertObjectTo(mapper,bookMap, Book.class);
        assertNotNull(book);
        assertEquals(book.getId(),"1");
    }
}
