package com.example.bookshelfapp.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Book;
import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.form.BookCreateForm;
import com.example.bookshelfapp.repository.BookRepository;
import com.example.bookshelfapp.repository.ProjectRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	private final ProjectRepository projectRepository;
	
	public BookService(BookRepository bookRepository,ProjectRepository projectRepository) {
		this.bookRepository = bookRepository;
		this.projectRepository = projectRepository;
	}
	
	public void addBook(Integer projectId, BookCreateForm form) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("プロジェクトが存在しません"));
    	
    	Book book = new Book();
    	book.setBookTitle(form.getBookTitle());
    	book.setAuthor(form.getAuthor());
    	book.setCompany(form.getCompany());
    	book.setPublicationDate(form.getPublicationDate());
		book.setBookStatus("unread");
		book.setCreatedAt(LocalDateTime.now());
		book.setExternalBookId("a1234");
		book.setProject(project);
    	
    	bookRepository.save(book);
	}

}
