package com.api.book.boot_rest_book.controller;

import java.util.List;

import com.api.book.boot_rest_book.entities.Book;
import com.api.book.boot_rest_book.services.Bookservice;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> getbooks()
    {
        // Book b=new Book();
        // b.setId(8);
        // b.setTitle("Harry Putter");
        // b.setAuthor("Amit");
        // return b;

        return this.bs.getAllBooks();
    }



    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id)
    {
        return bs.getBookById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book)
    {
        Book s=this.bs.addBook(book);
        return s;
    }

    @DeleteMapping("/books/{id}")
    public void deletebook(@PathVariable int id) 
    {
        this.bs.delBook(id);
    }

    @PutMapping("/books/{id}")
    public Book updatebook(@RequestBody Book book, @PathVariable int id) 
    {
        //TODO: process PUT request
        this.bs.updateBook(book,id);
        return book;
    }

}
