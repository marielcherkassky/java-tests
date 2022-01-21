package mariel.testsexamples.repository;

import mariel.testsexamples.models.Book;

import java.util.Optional;

public interface BookRepository {

    String saveBook(Book book);
    Optional<Book> getBookById(String id);

}
