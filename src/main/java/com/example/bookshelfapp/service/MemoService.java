package com.example.bookshelfapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.dto.MemoEditDTO;
import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.enums.MemoType;
import com.example.bookshelfapp.form.MemoEditForm;
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
		memo.setMemoType(MemoType.BOOK_MEMO);
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

	public MemoEditForm getMemoEditForm(Integer projectId, Integer memoId){
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(() -> new IllegalArgumentException("メモが存在しません"));

		if(!memo.getProject().getId().equals(projectId)){
			throw new IllegalArgumentException("指定プロジェクトに属さないメモです");
		}

		MemoEditForm form = new MemoEditForm();
		form.setId(memoId);
		form.setMemoTitle(memo.getMemoTitle());
		form.setMemoText(memo.getMemoText());

		return form;
	}

	public MemoEditDTO getMemoEditDTO(Integer projectId, Integer memoId){
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() ->new IllegalArgumentException("プロジェクトが存在しません"));
		
		Memo selectedMemo = memoRepository.findById(memoId)
			.orElseThrow(() -> new IllegalArgumentException("メモが存在しません"));

		if(!selectedMemo.getProject().getId().equals(projectId)){
			throw new IllegalArgumentException("指定プロジェクトに属さないメモです");
		}

		List<Memo> allMemos = memoRepository.findByProjectId(projectId);
	
		return new MemoEditDTO(project, allMemos, selectedMemo);
	}

	public void editMemo(Integer projectId, Integer memoId, MemoEditForm memoEditForm){
		Memo memo = memoRepository.findById(memoId)
			.orElseThrow(() -> new IllegalArgumentException("メモが存在しません"));

		if(!memo.getProject().getId().equals(projectId)){
			throw new IllegalArgumentException("指定プロジェクトに属さないメモです");
		}

		memo.setMemoText(memoEditForm.getMemoText());
		
		memoRepository.save(memo);
	}
	
	public Memo getMainWorkMemo(Integer projectId) {
		return memoRepository.findByProject_IdAndMemoType(projectId, MemoType.MAIN_WORK)
				.orElseThrow(() -> new IllegalArgumentException("メイン作業メモが存在しません"));
	}
	
	public Memo getProjectMemo(Integer projectId) {
		return memoRepository.findByProject_IdAndMemoType(projectId, MemoType.PROJECT_MEMO)
				.orElseThrow(() -> new IllegalArgumentException("プロジェクトメモが存在しません"));
	}

}
