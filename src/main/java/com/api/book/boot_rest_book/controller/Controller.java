package com.api.book.boot_rest_book.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import com.api.book.boot_rest_book.entities.Book;
import com.api.book.boot_rest_book.services.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class Controller {
    
@Autowired
private Bookservice bs;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getbooks()
    {
        // Book b=new Book();
        // b.setId(8);
        // b.setTitle("Harry Putter");
        // b.setAuthor("Amit");
        // return b;

        List<Book> list=bs.getAllBooks();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        return ResponseEntity.of(Optional.of(list));
    }



    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id)
    {
        Book b=bs.getBookById(id);
        if(b==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book s=null;
        
        try{
        s=this.bs.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/books/{id}")
    public void deletebook(@PathVariable int id) 
    {
        this.bs.delBook(id);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updatebook(@RequestBody Book book, @PathVariable int id) 
    {
        try{
        this.bs.updateBook(book,id);
        return ResponseEntity.ok().body(book);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
