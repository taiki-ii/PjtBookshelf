package com.example.bookshelfapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookshelfapp.service.ProjectWorkspaceService;

@Controller
public class ProjectWorkspaceController {
	
	private final ProjectWorkspaceService projectWorkspaceService;
	
	public ProjectWorkspaceController(ProjectWorkspaceService projectWorkspaceService) {
		this.projectWorkspaceService = projectWorkspaceService;
	}
	
	/**
     * プロジェクト一覧画面
     * 初期表示
	 * 
     */
	@GetMapping("/projects/{projectId}/workspace")
	public String showProjectWorkspace(
			@PathVariable Integer projectId,
			// @RequestParam(defalutValue = "shelf") String view,
			// @RequestParam(defaultValue = "recent") String scope,
			Model model) {
		
		model.addAttribute("projectId", projectId);
		// model.addAttribute("view", view);
		// model.addAttribute("scope, scope");
		
		// 共通情報
		model.addAttribute("workspaceInfo", projectWorkspaceService.getWorkspaceInfo(projectId));
		
		return "projects/workspace";
	}
}
