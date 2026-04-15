package com.example.bookshelfapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectEditForm {
	
	@NotNull(message = "IDが取得できませんでした")
	private Integer id;
	
	@NotBlank(message = "プロジェクト名は必須です")
	private String projectName;
	
	@NotBlank(message = "ステータスは必須です")
	private String status;
	
}
