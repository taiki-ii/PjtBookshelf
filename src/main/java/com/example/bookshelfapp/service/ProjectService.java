package com.example.bookshelfapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookshelfapp.entity.Project;
import com.example.bookshelfapp.entity.User;
import com.example.bookshelfapp.form.ProjectCreateForm;
import com.example.bookshelfapp.form.ProjectEditForm;
import com.example.bookshelfapp.repository.ProjectRepository;
import com.example.bookshelfapp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MemoService memoService;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, MemoService memoService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.memoService = memoService;
    }

    // プロジェクト一覧表示
    public List<Project> getProjectList() {
        return projectRepository.findAll();
    }
    
    // プロジェクト新規作成
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

        memoService.createMainMemo(project);
        memoService.createProjectMemo(project);
    }
    
    // プロジェクト一覧表示
    public List<Project> findAll(){
    	return projectRepository.findAll();
    }
    
    // 選択プロジェクト表示
    public Project findById(Integer projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("指定したプロジェクトが存在しません。 id=" + projectId));
    }

    // プロジェクト名変更
    @Transactional
    public void updateProject(ProjectEditForm form) {
        Project project = projectRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalArgumentException("更新対象のプロジェクトが存在しません。 id=" + form.getId()));

        project.setProjectName(form.getProjectName());
        project.setStatus(form.getStatus());

        projectRepository.save(project);
    }

}