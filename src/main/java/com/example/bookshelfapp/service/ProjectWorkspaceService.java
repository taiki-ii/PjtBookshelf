package com.example.bookshelfapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.dto.WorkspaceDTO;
import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.repository.BookRepository;
import com.example.bookshelfapp.repository.MemoRepository;
import com.example.bookshelfapp.repository.ProjectRepository;
import com.example.bookshelfapp.repository.UserRepository;

@Service
public class ProjectWorkspaceService {
	
	private final ProjectRepository projectRepository;
	private final BookRepository bookRepository;
	private final MemoRepository memoRepository;
	//private final UserRepository userRepository;
	
	public ProjectWorkspaceService(ProjectRepository projectRepository,
								   BookRepository bookRepository,
								   MemoRepository memoRepository,
								   UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.bookRepository = bookRepository;
		this.memoRepository = memoRepository;
		//this.userRepository = userRepository;
	}
	
	public WorkspaceDTO getWorkspaceInfo(Integer projectId) {
		Project project = projectRepository.findById(projectId)
		.orElseThrow(() -> new IllegalArgumentException("指定したプロジェクトが存在しません。 id=" + projectId));
		
		// 本・メモ全件取得
		List<Book> allBooks = bookRepository.findByProjectId(projectId);
		List<Memo> allMemos = memoRepository.findByProjectId(projectId);
		// 本・メモ直近の3件
		List<Book> recentBooks = bookRepository.findTop3ByProjectIdOrderByUpdatedAtDesc(projectId);
		List<Memo> recentMemos = memoRepository.findTop3ByProjectIdOrderByUpdatedAtDesc(projectId);
		// 本と紐づくメモ取得
		List<Memo> bookLinkedMemos = memoRepository.findByProjectIdAndBookIsNotNull(projectId);
		
		
		// 本・メモ数取得
	    long bookCount = bookRepository.countByProjectId(projectId);
	    long memoCount = memoRepository.countByProjectId(projectId);
	    // プロジェクト最終更新日時取得
	    LocalDateTime updatedAt = project.getUpdatedAt();
	    
		return new WorkspaceDTO(project, recentBooks, recentMemos, allBooks, bookLinkedMemos, allMemos, bookCount, memoCount, updatedAt);
	}
}
