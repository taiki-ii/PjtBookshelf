package com.example.bookshelfapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectCreateForm {
	
	@NotBlank(message = "プロジェクト名を入力してください。")
	@Size(max = 255, message = "プロジェクト名は255文字以内で入力してください。")
	private String projectName;
	
}
