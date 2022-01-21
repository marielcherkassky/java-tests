package mariel.testsexamples.services;

import mariel.testsexamples.models.Book;
import mariel.testsexamples.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    private BookRepository bookRepository;

    public BooksService(@Autowired BookRepository booksRepo){
        this.bookRepository = booksRepo;
    }

    public Book getBookById(String id){
        var optional = bookRepository.getBookById(id);
        return optional.orElse(null);
    }

    public String saveBook(Book book){
        return this.bookRepository.saveBook(book);
    }

}
