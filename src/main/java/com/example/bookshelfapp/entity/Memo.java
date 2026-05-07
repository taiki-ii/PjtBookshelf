package com.example.bookshelfapp.entity;

import java.time.LocalDateTime;

import com.example.bookshelfapp.enums.MemoType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "memos")
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "memo_title", nullable = false, length = 255)
    private String memoTitle;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "memo_type" , nullable = false)
    private MemoType memoType;
    
    @Column(name = "memo_text", nullable = false, length = 30000)
    private String memoText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Memo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }    
    
    public MemoType getMemoType() {
    	return memoType;
    }
    
    public void setMemoType(MemoType memoType) {
    	this.memoType = memoType;
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}