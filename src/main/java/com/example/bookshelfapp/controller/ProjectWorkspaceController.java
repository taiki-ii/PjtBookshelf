package com.example.bookshelfapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookshelfapp.dto.WorkspaceDTO;
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
        
        
        
		
		return "projects/workspace";
	}
}
