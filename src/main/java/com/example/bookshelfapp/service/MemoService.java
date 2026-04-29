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
		if(memoRepository.existsByBook_Id(bookId)){
			throw new IllegalArgumentException("この本には既にメモが登録されています");
		}
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

	public void deleteMemo(Integer projectId, Integer memoId){

		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(() -> new IllegalArgumentException("メモが存在しません"));

		if (!memo.getProject().getId().equals(projectId)) {
			throw new IllegalArgumentException("不正な操作です");
		}

		if (memo.getBook() == null) {
        throw new IllegalArgumentException("このメモは削除対象ではありません");
    	}

		memoRepository.delete(memo);
	}

}
