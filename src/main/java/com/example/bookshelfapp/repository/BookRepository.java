package com.example.bookshelfapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	// プロジェクト単位で本を取得
	List<Book> findByProjectId(Integer projectId);
	
	// 作成日時の新しい順で取得（直近表示用）
	List<Book> findByProjectIdOrderByCreatedAtDesc(Integer projectId);
	
}
