package com.example.bookshelfapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.example.bookshelfapp.dto.BookSearchResultDto;
import com.example.bookshelfapp.service.BookService;

@Controller
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping("/books/search")
	public String showSearchPage() {
	    return "books/search";
	}
	
    @GetMapping("/books/search/result")
    public String searchBooks(
            @RequestParam String keyword,
            Model model) {

        try {
            List<BookSearchResultDto> books = bookService.searchBooks(keyword);

            model.addAttribute("books", books);
            model.addAttribute("keyword", keyword);

            if (books.isEmpty()) {
                model.addAttribute("message", "検索結果がありませんでした。");
            }

        } catch (HttpClientErrorException.TooManyRequests e) {
            model.addAttribute("errorMessage", "APIの利用上限に達しました。時間をおいて再度お試しください。");

        } catch (Exception e) {
            model.addAttribute("errorMessage", "本検索中にエラーが発生しました。");
        }

        return "books/search";
    }
}
