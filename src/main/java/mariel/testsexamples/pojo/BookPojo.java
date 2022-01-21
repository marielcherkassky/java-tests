package mariel.testsexamples.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mariel.testsexamples.models.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookPojo {

    private String name;
    private String author;
    private List<Genre> genre;

}
