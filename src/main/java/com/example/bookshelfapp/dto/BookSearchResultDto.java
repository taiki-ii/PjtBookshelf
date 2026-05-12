package com.example.bookshelfapp.dto;

public class BookSearchResultDto {
	
    private String title;
    private String author;
    private String thumbnail;

    public BookSearchResultDto(String title, String author, String thumbnail) {
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getThumbnail() { return thumbnail; }
}
