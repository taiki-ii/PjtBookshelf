package com.example.bookshelfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer> {

}
