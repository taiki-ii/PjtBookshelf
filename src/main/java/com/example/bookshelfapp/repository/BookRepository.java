package com.example.bookshelfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
