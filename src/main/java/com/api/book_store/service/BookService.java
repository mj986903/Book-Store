package com.api.book_store.service;

import com.api.book_store.dao.BookDao;
import com.api.book_store.model.Book;
import com.api.book_store.model.BookRequest;
import com.api.book_store.model.BooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public ResponseEntity<BooksResponse> getAllBooks(int currentPage, int rowsPerPage){
        BooksResponse response = new BooksResponse();
        response.setCurrentPage(currentPage);
        response.setRowsPerPage(rowsPerPage);
        try{
            if(currentPage <= 0 || rowsPerPage <= 0){
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            int totalCount = (int) bookDao.count();
            List<Book> books =  bookDao.getAllBooks((currentPage-1)*rowsPerPage,rowsPerPage);
            response.setBooks(books);
            response.setTotalPages((totalCount/rowsPerPage)+1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addBook(BookRequest book){
        try{
            if(book.getTitle() == null){
                return new ResponseEntity<>("Book title is required.", HttpStatus.BAD_REQUEST);
            }
            if(!isValidISBN((book.getIsbn()))){
                return new ResponseEntity<>("Book ISBN must have 10 or 13 digits.", HttpStatus.BAD_REQUEST);
            }
            if(bookDao.findByIsbn(book.getIsbn()) != null){
                return new ResponseEntity<>("Book ISBN must be unique.", HttpStatus.BAD_REQUEST);
            }
            if(book.getPrice() <= 0){
                return new ResponseEntity<>("Book price must be greater than zero.", HttpStatus.BAD_REQUEST);
            }
            Book newBook = new Book();
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setIsbn(book.getIsbn());
            newBook.setPrice(book.getPrice());
            bookDao.save(newBook);
            return new ResponseEntity<>("Book added successfully.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Book not added." + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private static boolean isValidISBN(long isbn) {
        int count = 0;
        long temp = isbn;
        while (temp != 0) {
            count++;
            temp /= 10;
        }
        return count == 10 || count == 13;
    }

    public ResponseEntity<Optional<Book>> getBook(int bookId){
        try{
            Optional<Book> book = bookDao.findById(bookId);
            if(book.isPresent()){
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
            return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Book> searchBook(String title){
        try{
            Book book = bookDao.findByTitleIgnoreCase(title);
            if(book != null){
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateBook(int bookId,BookRequest book){
        try{
            if(book.getTitle() == null){
                return new ResponseEntity<>("Book not update. \nBook title is required.", HttpStatus.BAD_REQUEST);
            }
            if(!isValidISBN((book.getIsbn()))){
                return new ResponseEntity<>("Book not update. \nBook ISBN must have 10 or 13 digits.", HttpStatus.BAD_REQUEST);
            }
            Book anyIsbnBook = bookDao.findByIsbn(book.getIsbn());
            if(anyIsbnBook != null && anyIsbnBook.getId() != bookId){
                return new ResponseEntity<>("Book not update. \nBook ISBN must be unique.", HttpStatus.BAD_REQUEST);
            }
            if(book.getPrice() <= 0){
                return new ResponseEntity<>("Book not update. \nBook price must be greater than zero.", HttpStatus.BAD_REQUEST);
            }
            Book updateBook = bookDao.findById(bookId).get();
            updateBook.setTitle(book.getTitle());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setIsbn(book.getIsbn());
            updateBook.setPrice(book.getPrice());
            bookDao.save(updateBook);
            return new ResponseEntity<>("Book updated successfully.", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Book not update.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteBook(int bookId){
        try{
            Optional<Book> book = bookDao.findById(bookId);
            if(book.isPresent()){
                bookDao.delete(book.get());
                return new ResponseEntity<>("Book deleted successfully.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Book not deleted.",HttpStatus.BAD_REQUEST);
        }
    }
}
