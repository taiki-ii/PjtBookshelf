package com.example.bookshelfapp.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.repository.BookRepository;
import com.example.bookshelfapp.repository.MemoRepository;
import com.example.bookshelfapp.repository.ProjectRepository;

@Service
public class MemoService {

    private final MemoRepository memoRepository;
    private final ProjectRepository projectRepository;
    private final BookRepository bookRepository;
    
    public MemoService(MemoRepository memoRepository, ProjectRepository projectRepository, BookRepository bookRepository) {
    	this.memoRepository = memoRepository;
    	this.projectRepository = projectRepository;
    	this.bookRepository = bookRepository;
    }

    public void addMemo(Integer projectId, Integer bookId){
 		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("プロジェクトが存在しません"));
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> new IllegalArgumentException("本が存在しません"));
		String memoTitle = book.getBookTitle();
            
		Memo memo = new Memo();
		memo.setMemoTitle(memoTitle);
		memo.setBook(book);
		memo.setProject(project);
        memo.setMemoText(memoTitle + "の作業メモです。");   
        memo.setCreatedAt(LocalDateTime.now());
        
        memoRepository.save(memo);
    }


}
