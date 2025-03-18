package com.api.book_store.controller;

import com.api.book_store.model.Book;
import com.api.book_store.model.BookRequest;
import com.api.book_store.model.BooksResponse;
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
    public ResponseEntity<BooksResponse> getAllBooks(@RequestParam int currentPage, @RequestParam int rowsPerPage){
        return bookService.getAllBooks(currentPage,rowsPerPage);
    }

    @PostMapping("addBook")
    public ResponseEntity<String> addBook(@RequestBody BookRequest book){
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

    @PutMapping("updateBook/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable int bookId,@RequestBody BookRequest book){
        return bookService.updateBook(bookId,book);
    }

    @DeleteMapping("deleteBook/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId){
        return bookService.deleteBook(bookId);
    }
}
