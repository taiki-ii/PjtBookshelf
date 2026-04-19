package com.example.bookshelfapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
	
	// プロジェクト単位でメモ取得
	List<Memo> findByProjectId(Integer projectId);
	
	// プロジェクト単位でメモ数取得
	long countByProjectId(Integer projectId);
	
	// 新しい順
	List<Memo> findByProjectIdOrderByCreatedAtDesc(Integer projectId);
	

}
