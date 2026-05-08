package com.example.bookshelfapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.enums.MemoType;
import com.example.bookshelfapp.repository.BookRepository;
import com.example.bookshelfapp.repository.MemoRepository;
import com.example.bookshelfapp.repository.ProjectRepository;

class MemoServiceTest {
	
	@Mock
	private MemoRepository memoRepository;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Mock
	private BookRepository bookRepository;
	
	private MemoService memoService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		memoService = new MemoService(memoRepository, projectRepository, bookRepository);
	}
	
	@Test
	void getMainWorkMemo_メイン作業メモが存在する場合_Memoを返す() {
		// Arrange
		// 前提条件
		Integer projectId = 1;
		
		// テストデータ
		Memo memo = new Memo();
		memo.setId(10);
		memo.setMemoType(MemoType.MAIN_WORK);
		memo.setMemoText("メイン作業メモです");
		
		// Mockの戻り値を用意
		when(memoRepository.findByProject_IdAndMemoType(projectId, MemoType.MAIN_WORK))
			.thenReturn(Optional.of(memo));
		
		// Act
		Memo result = memoService.getMainWorkMemo(projectId);
		
		// Assert
		assertEquals(10, result.getId());
		assertEquals(MemoType.MAIN_WORK, result.getMemoType());
		assertEquals("メイン作業メモです",result.getMemoText());
		
		verify(memoRepository).findByProject_IdAndMemoType(projectId, MemoType.MAIN_WORK);
	}
	
	@Test
	void getMainWorkMemo_メイン作業メモが存在しない場合_例外を投げる() {
		// Arrange
		// 前提条件（引数）
		Integer projectId = 1;
		
		// Mockの戻り値を用意
		when(memoRepository.findByProject_IdAndMemoType(projectId, MemoType.MAIN_WORK))
			.thenReturn(Optional.empty());
		
		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> memoService.getMainWorkMemo(projectId)
		);
		
		assertEquals("メイン作業メモが存在しません", exception.getMessage());
		
		verify(memoRepository).findByProject_IdAndMemoType(projectId, MemoType.MAIN_WORK);
	}
	
	@Test
	void getProjectMemoプロジェクトメモが存在する場合_例外を投げる() {
		// Arrange
		// 前提条件
		Integer projectId = 1;
		
		// テストデータ作成（memoType=PROJECT_MEMOをもつメモ）
		Memo memo = new Memo();
		memo.setId(20);
		memo.setMemoType(MemoType.PROJECT_MEMO);
		memo.setMemoText("プロジェクトメモです");
		
		// Mockの戻り値を用意
        when(memoRepository.findByProject_IdAndMemoType(projectId, MemoType.PROJECT_MEMO))
        .thenReturn(Optional.of(memo));
        
        // Act
        Memo result = memoService.getProjectMemo(projectId);
        
        // Assert
        assertEquals(20, result.getId());
        assertEquals(MemoType.PROJECT_MEMO, result.getMemoType());
        assertEquals("プロジェクトメモです", result.getMemoText());
        
        verify(memoRepository).findByProject_IdAndMemoType(projectId, MemoType.PROJECT_MEMO);
	}
	
	@Test
	void getProjectMemo_プロジェクトメモが存在しない場合_例外を投げる() {
		// Arrange
		// 前提条件
		Integer projectId = 1;
		
		// Mockの戻り値を用意
		when(memoRepository.findByProject_IdAndMemoType(projectId, MemoType.PROJECT_MEMO))
		.thenReturn(Optional.empty());
		
		// Act & Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> memoService.getProjectMemo(projectId)
		);
		
		assertEquals("プロジェクトメモが存在しません", exception.getMessage());
		
		verify(memoRepository).findByProject_IdAndMemoType(projectId, MemoType.PROJECT_MEMO);
	}
	
}
