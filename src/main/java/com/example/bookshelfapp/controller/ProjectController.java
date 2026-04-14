package com.example.bookshelfapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.service.ProjectService;

@Controller
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	public String showProjectList(Model model) {
		List<Project> projectList = projectService.getProjectList();
		model.addAttribute("projectList", projectList);
		return "projects/list";
	}
}
