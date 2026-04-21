package com.example.bookshelfapp.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookCreateForm {
	@NotBlank(message = "プロジェクト名を入力してください")
	@Size(max =255, message = "題名は255字以内で入力してください。")
	private String bookTitle;
	
	@NotBlank(message = "作者を入力してください")
	@Size(max =255, message = "作者は255字以内で入力してください。")
	private String author;
	
	@Size(max =255, message = "出版社は255字以内で入力してください。")
	private String company;
	
	private LocalDate publicationDate;
}
