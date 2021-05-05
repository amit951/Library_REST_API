package com.api.book.boot_rest_book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.api.book.boot_rest_book.entities.Book;

import org.springframework.stereotype.Component;

@Component
public class Bookservice {
  
    static private List<Book> list=new ArrayList<>();

    static{

        list.add(new Book(455,"Life of Agnis", "Agnis"));
        list.add(new Book(67,"Life of Satish", "Satish"));
        list.add(new Book(34,"Life of Pie", "Pie"));

    }


    public List<Book> getAllBooks()
    {
        return list;
    }

    public Book getBookById(int id)
    {
        Book book=null;
        book=list.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }


    public Book addBook(Book b)
    {
        list.add(b);
        return b;
    }


    public void delBook(int id)
    {
        list=list.stream().filter(book->book.getId()!=id).collect(Collectors.toList());
    }



    public void updateBook( Book b, int id)
    {

        list=list.stream().map(x->{

            if(x.getId()==id)
            {
                x.setTitle(b.getTitle());
                x.setAuthor(b.getAuthor());
            }

            return x;

        }).collect(Collectors.toList());
    }
}
