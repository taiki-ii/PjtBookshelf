package com.example.bookshelfapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.form.ProjectCreateForm;
import com.example.bookshelfapp.service.ProjectService;

import jakarta.validation.Valid;

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
		model.addAttribute("projectCreateForm", new ProjectCreateForm());
		return "projects/list";
	}
	
	@PostMapping("/create-new-project")
	public String registNewProject(
			@Valid @ModelAttribute("projectCreateForm") ProjectCreateForm form,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "projects/list";
			
		}
		
		projectService.createProject(form);
		return "redirect:/projects";
	}
}
