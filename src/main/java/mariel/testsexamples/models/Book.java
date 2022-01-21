package mariel.testsexamples.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Book {
    private String id;
    private String name;
    private String author;
    private Set<Genre> genre;

}

