package com.example.bookshelfapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	// プロジェクト単位で本を全件取得
	List<Book> findByProjectId(Integer projectId);
	// プロジェクト単位で本を直近3件取得
	List<Book> findTop3ByProjectIdOrderByUpdatedAtDesc(Integer projectId);
	Optional<Book> findTopByProjectIdOrderByUpdatedAtDesc(Integer projectId);
	
	// プロジェクト単位で本冊数を取得
	long countByProjectId(Integer projectId);
	
	// 作成日時の新しい順で取得（直近表示用）
	// List<Book> findByProjectIdOrderByCreatedAtDesc(Integer projectId);
	
}
