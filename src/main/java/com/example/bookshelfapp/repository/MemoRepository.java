package com.example.bookshelfapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.enums.MemoType;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
	
	List<Memo> findByProjectId(Integer projectId);
	
	List<Memo> findTop3ByProjectIdOrderByUpdatedAtDesc(Integer projectId);
	Optional<Memo> findByProjectIdOrderByUpdatedAtDesc(Integer projectId);

	List<Memo> findByProject_IdAndBookIsNotNull(Integer projectId);
	
	long countByProjectId(Integer projectId);
	
	List<Memo> findByProjectIdOrderByCreatedAtDesc(Integer projectId);

	boolean existsByBook_Id(Integer bookId);
	
	Optional<Memo> findByProject_IdAndMemoType(Integer projectId, MemoType memoType);

}
