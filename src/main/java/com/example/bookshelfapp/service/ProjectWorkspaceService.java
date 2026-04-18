package com.example.bookshelfapp.service;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.repository.ProjectRepository;
import com.example.bookshelfapp.repository.UserRepository;

@Service
public class ProjectWorkspaceService {
	
	private final ProjectRepository projectRepository;
	//private final UserRepository userRepository;
	
	public ProjectWorkspaceService(ProjectRepository projectRepository,UserRepository userRepository) {
		this.projectRepository = projectRepository;
		//this.userRepository = userRepository;
	}
	
	public Project getWorkspaceInfo(Integer projectId) {
		return projectRepository.findById(projectId)
		.orElseThrow(() -> new IllegalArgumentException("指定したプロジェクトが存在しません。 id=" + projectId));
	}

}
