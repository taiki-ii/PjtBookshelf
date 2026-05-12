package com.example.bookshelfapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bookshelfapp.dto.BookSearchResultDto;
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
	
	// 本追加
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

	// 本削除
	public void deleteBook(Integer projectId, Integer bookId){
		Book book = bookRepository.findById(bookId)
			.orElseThrow(() -> new IllegalArgumentException("本が存在しません"));
		
		if(!book.getProject().getId().equals(projectId)){
			throw new IllegalArgumentException("指定プロジェクトに属さない本です");
		}
		
		bookRepository.delete(book);
	}
	
	// 本検索
    public List<BookSearchResultDto> searchBooks(String keyword) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + keyword;

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response =
                restTemplate.getForObject(url, Map.class);

        List<BookSearchResultDto> results = new ArrayList<>();

        List<Map<String, Object>> items =
                (List<Map<String, Object>>) response.get("items");

        if (items == null) {
            return results;
        }

        for (Map<String, Object> item : items) {

            Map<String, Object> volumeInfo =
                    (Map<String, Object>) item.get("volumeInfo");

            String title = (String) volumeInfo.get("title");

            List<String> authors = (List<String>) volumeInfo.get("authors");
            String author = (authors != null && !authors.isEmpty())
                    ? authors.get(0)
                    : "不明";

            Map<String, String> imageLinks =
                    (Map<String, String>) volumeInfo.get("imageLinks");

            String thumbnail = (imageLinks != null)
                    ? imageLinks.get("thumbnail")
                    : null;

            results.add(new BookSearchResultDto(title, author, thumbnail));
        }

        return results;
    }

}
