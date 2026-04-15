package com.example.bookshelfapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.entity.User;
import com.example.bookshelfapp.form.ProjectCreateForm;
import com.example.bookshelfapp.repository.ProjectRepository;
import com.example.bookshelfapp.repository.UserRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }
    
    public void createProject(ProjectCreateForm form) {
    	// 開発用に仮UserIdを登録する
    	User user = userRepository.findById(1)
    			.orElseThrow(()-> new IllegalArgumentException("ユーザーが存在しません。"));
    	
    	Project project = new Project();
    	project.setProjectName(form.getProjectName());
    	project.setStatus("unstarted");
    	project.setCreatedAt(LocalDateTime.now());
    	project.setUser(user);
    	
    	projectRepository.save(project);
    }
    
}