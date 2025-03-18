package com.api.book_store.dao;

import com.api.book_store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {
    Book findByIsbn(long isbn);
    Book findByTitleIgnoreCase(String title);

    @Query(value = "SELECT * FROM Book LIMIT :take OFFSET :skip",nativeQuery = true)
    List<Book> getAllBooks(int skip,int take);
}
