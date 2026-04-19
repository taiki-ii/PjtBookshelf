package com.example.bookshelfapp.dto;

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
	
	public WorkspaceDTO(Project project, List<Book> books, List<Memo> memos, long bookCount, long memoCount) {
		this.project = project;
		this.books = books;
		this.memos = memos;
        this.bookCount = bookCount;
        this.memoCount = memoCount;
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

}
