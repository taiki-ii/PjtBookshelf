package com.example.bookshelfapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
	
	// プロジェクト単位でメモ取得
	List<Memo> findByProjectId(Integer projectId);
	// プロジェクト単位でメモを直近3件取得
	List<Memo> findTop3ByProjectIdOrderByUpdatedAtDesc(Integer projectId);
	Optional<Memo> findByProjectIdOrderByUpdatedAtDesc(Integer projectId);

	List<Memo> findByProject_IdAndBookIsNotNull(Integer projectId);
	
	// プロジェクト単位でメモ数取得
	long countByProjectId(Integer projectId);
	
	// 新しい順
	List<Memo> findByProjectIdOrderByCreatedAtDesc(Integer projectId);

	boolean existsByBook_Id(Integer bookId);

}
