package com.example.bookshelfapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;

public class WorkspaceDTO {
	
	private Project project;
	private List<Book> books;
	private List<Memo> memos;
	private long bookCount;
	private long memoCount;
	private LocalDateTime updatedAt;
	
	public WorkspaceDTO(Project project, List<Book> books, List<Memo> memos, long bookCount, long memoCount, LocalDateTime updatedAt) {
		this.project = project;
		this.books = books;
		this.memos = memos;
        this.bookCount = bookCount;
        this.memoCount = memoCount;
        this.updatedAt = updatedAt;
        
	}
	
    public Project getProject() {
        return project;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Memo> getMemos() {
        return memos;
    }
    
    public long getBookCount() {
        return bookCount;
    }

    public long getMemoCount() {
        return memoCount;
    }
    
    public LocalDateTime getUpdatedAt() {
    	return updatedAt;
    }

}
