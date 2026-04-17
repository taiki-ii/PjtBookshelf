package com.example.bookshelfapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.form.ProjectCreateForm;
import com.example.bookshelfapp.form.ProjectEditForm;
import com.example.bookshelfapp.service.ProjectService;

import jakarta.validation.Valid;

@Controller
public class ProjectController {
	
	private final ProjectService projectService;
	
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	/**
     * プロジェクト一覧画面
     * 初期表示
	 * 
     */
	@GetMapping("/projects")
	public String showProjectList(Model model) {
		List<Project> projectList = projectService.getProjectList();
		model.addAttribute("projectList", projectList);
		model.addAttribute("projectEditForm", new ProjectEditForm());
		model.addAttribute("projectCreateForm", new ProjectCreateForm());
		return "projects/list";
	}
	
    /**
     * 作成ボタン押下時
     * 
     */
	@PostMapping("/create-new-project")
	public String registNewProject(
			@Valid @ModelAttribute("projectCreateForm") ProjectCreateForm form,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("projectList", projectService.getProjectList());
			model.addAttribute("projectEditForm", new ProjectEditForm());
			return "projects/list";
		}
		
		projectService.createProject(form);
		return "redirect:/projects";
	}
	
    /**
     * 編集ボタン押下時
     * projectId を受け取り、
     * formクラス、Modelの順で値を詰め、
     * 対象データを編集フォームに反映させて一覧画面を再表示
     */
	@GetMapping("/project-update")
	public String showEditForm(@RequestParam("projectId") Integer ProjectId, Model model) {
		List<Project> projectList = projectService.findAll();
		Project project = projectService.findById(ProjectId);
		
		ProjectEditForm form = new ProjectEditForm();
		form.setId(project.getId());
		form.setProjectName(project.getProjectName());
		form.setStatus(project.getStatus());
		
		model.addAttribute("projectList",projectList);
		model.addAttribute("project",project);
		model.addAttribute("projectCreateForm", new ProjectCreateForm());
		model.addAttribute("projectEditForm", form);
		
		return "projects/list";
	}
	
    /**
     * 更新ボタン押下時
     * 
     */
    @PostMapping("/project-update")
    public String updateProject(
            @Valid @ModelAttribute("projectEditForm") ProjectEditForm projectEditForm,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            List<Project> projectList = projectService.findAll();
            model.addAttribute("projectList", projectList);
            model.addAttribute("projectCreateForm", new ProjectCreateForm());
            return "projects/list";
        }

        projectService.updateProject(projectEditForm);
        return "redirect:/projects";
    }

}
