package com.example.bookshelfapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;

public class WorkspaceDTO {
	
	private Project project;
	private List<Book> recentBooks;
	private List<Memo> recentMemos;
	private List<Book> allBooks;
	private List<Memo> allMemos;
	private long bookCount;
	private long memoCount;
	private LocalDateTime updatedAt;
	
	public WorkspaceDTO
				(Project project, 
				List<Book> recentBooks, 
				List<Memo> recentMemos, 
				List<Book> allBooks, 
				List<Memo> allMemos, 
				long bookCount, 
				long memoCount, 
				LocalDateTime updatedAt) {
		this.project = project;
		this.recentBooks = recentBooks;
		this.recentMemos = recentMemos;
		this.allBooks = allBooks;
		this.allMemos = allMemos;
        this.bookCount = bookCount;
        this.memoCount = memoCount;
        this.updatedAt = updatedAt;
        
	}
	
    public Project getProject() {
        return project;
    }

    public List<Book> getRecentBooks() {
        return recentBooks;
    }

    public List<Memo> getRecentMemos() {
        return recentMemos;
    }
    
    public List<Book> getAllBooks() {
        return allBooks;
    }

    public List<Memo> getAllMemos() {
        return allMemos;
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
