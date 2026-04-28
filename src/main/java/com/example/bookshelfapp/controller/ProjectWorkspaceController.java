package com.example.bookshelfapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookshelfapp.dto.WorkspaceDTO;
import com.example.bookshelfapp.form.BookCreateForm;
import com.example.bookshelfapp.service.BookService;
import com.example.bookshelfapp.service.MemoService;
import com.example.bookshelfapp.service.ProjectWorkspaceService;

import jakarta.validation.Valid;

@Controller
public class ProjectWorkspaceController {
	
	private final ProjectWorkspaceService projectWorkspaceService;
	private final BookService bookService;
	private final MemoService memoService;
	
	public ProjectWorkspaceController(ProjectWorkspaceService projectWorkspaceService, BookService bookService,MemoService memoService) {
		this.projectWorkspaceService = projectWorkspaceService;
		this.bookService = bookService;
		this.memoService = memoService;
	}
	
	/**
     * プロジェクト一覧画面
     * 初期表示
	 * 
     */
	@GetMapping("/projects/{projectId}/workspace")
	public String showProjectWorkspace(
			@PathVariable Integer projectId,
			@RequestParam(defaultValue = "shelf") String view,
			@RequestParam(defaultValue = "recent") String scope,
			Model model) {
		
		model.addAttribute("view", view);
		model.addAttribute("scope", scope);
		
		WorkspaceDTO workspace = projectWorkspaceService.getWorkspaceInfo(projectId);
		// 共通情報
		// model.addAttribute("workspaceInfo", projectWorkspaceService.getWorkspaceInfo(projectId));
		model.addAttribute("updatedAt", workspace.getUpdatedAt());
		
		// 本直近表示（作業棚）
        model.addAttribute("projectId", projectId);
        model.addAttribute("workspace", workspace);
        
        model.addAttribute("bookCreateForm", new BookCreateForm());
        model.addAttribute("isBookModalOpen", false);
        
		return "projects/workspace";
	}

	/**
     * プロジェクト詳細画面
     * 本追加
	 * 
     */
	@PostMapping("/projects/{projectId}/books")
	public String addBooks(
			@PathVariable Integer projectId,
			@Valid @ModelAttribute("bookCreateForm") BookCreateForm form,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
	        model.addAttribute("projectId", projectId);
	        model.addAttribute("view", "shelf");
	        model.addAttribute("scope", "recent");
	        model.addAttribute("workspace", projectWorkspaceService.getWorkspaceInfo(projectId));
	        model.addAttribute("isBookModalOpen", true);
	        return "projects/workspace";				
		}
	
		bookService.addBook(projectId, form);
		return "redirect:/projects/" + projectId + "/workspace?view=shelf";
	}

	/**
     * プロジェクト詳細画面
     * 本削除
	 * 
     */
	@PostMapping("/projects/{projectId}/books/delete")
	public String deleteBooks(
		@PathVariable Integer projectId,
		@RequestParam Integer bookId){
			bookService.deleteBook(projectId, bookId);
			return "redirect:/projects/" + projectId + "/workspace?view=shelf";
		}

	/**
     * プロジェクト詳細画面
     * メモ追加
	 * 
     */
	@PostMapping("/projects/{projectId}/memos")
	public String addBook(
		@PathVariable Integer projectId,
		@RequestParam Integer bookId,
		Model model){

			try{
			memoService.addMemo(projectId, bookId);
			return "redirect:/projects/" + projectId + "/workspace?view=shelf";
			} catch(IllegalArgumentException e) {

				WorkspaceDTO workspace = projectWorkspaceService.getWorkspaceInfo(projectId);

				// 共通情報
				model.addAttribute("updatedAt", workspace.getUpdatedAt());
				
				// 本直近表示（作業棚）
		        model.addAttribute("projectId", projectId);
		        model.addAttribute("workspace", workspace);
				model.addAttribute("view", "memos");
		     
		        model.addAttribute("bookCreateForm", new BookCreateForm());
		        model.addAttribute("isBookModalOpen", false);
				model.addAttribute("errorMessage", e.getMessage());

				return "projects/workspace";
			}

		}

}
