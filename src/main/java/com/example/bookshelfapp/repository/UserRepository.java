package com.example.bookshelfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
