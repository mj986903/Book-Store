package com.api.book_store.controller;

import com.api.book_store.model.Book;
import com.api.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book-store")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("allBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("allBooksWithPagination")
    public ResponseEntity<List<Book>> getAllBooksWithPagination(@RequestParam int currentPage,@RequestParam int rowsPerPage){
        return bookService.getAllBooksWithPagination(currentPage,rowsPerPage);
    }

    @PostMapping("addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @GetMapping("getBook/{bookId}")
    public ResponseEntity<Optional<Book>> getBook(@PathVariable int bookId){
        return  bookService.getBook(bookId);
    }

    @GetMapping("searchBook")
    public ResponseEntity<Book> searchBook(@RequestParam String title){
        return  bookService.searchBook(title);
    }

    @PutMapping("updateBook")
    public ResponseEntity<String> updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("deleteBook/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId){
        return bookService.deleteBook(bookId);
    }
}
