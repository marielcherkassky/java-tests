package mariel.testsexamples.controllers;

import mariel.testsexamples.models.Book;
import mariel.testsexamples.pojo.BookPojo;
import mariel.testsexamples.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.UUID;

@RequestMapping("/books")
@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @PostMapping(value = "save",consumes="application/json")
    public ResponseEntity saveBook(@RequestBody BookPojo pojo){
        var book = new Book(UUID.randomUUID().toString(),pojo.getName(),pojo.getAuthor(),new HashSet<>(pojo.getGenre()));
        var result = booksService.saveBook(book);
        if(result == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("get/{id}")
    public ResponseEntity  getBook(@PathVariable(value="id") String id){
        var result =  booksService.getBookById(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
