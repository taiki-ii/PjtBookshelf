package com.example.bookshelfapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	

}
