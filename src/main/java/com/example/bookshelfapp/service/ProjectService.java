package com.example.bookshelfapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }
}