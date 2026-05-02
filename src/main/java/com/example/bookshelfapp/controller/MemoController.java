package com.example.bookshelfapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookshelfapp.dto.MemoEditDTO;
import com.example.bookshelfapp.form.MemoEditForm;
import com.example.bookshelfapp.service.BookService;
import com.example.bookshelfapp.service.MemoService;
import com.example.bookshelfapp.service.ProjectService;
import com.example.bookshelfapp.service.ProjectWorkspaceService;

@Controller
public class MemoController {

    private final ProjectService projectService;
    private final BookService bookService;
    private final MemoService memoService;
    private final ProjectWorkspaceService projectWorkspaceService;

    public MemoController(ProjectService projectService,BookService bookService, MemoService memoService, ProjectWorkspaceService projectWorkspaceService){
        this.projectService = projectService;
        this.bookService = bookService;
        this.memoService = memoService;
        this.projectWorkspaceService = projectWorkspaceService;
    }

    @PostMapping("/projects/{projectId}/memos/{memoId}/edit")
    public String editMemo(
        @PathVariable Integer projectId,
        @PathVariable Integer memoId,
        MemoEditForm memoEditForm,
        Model model){

    		memoService.editMemo(projectId, memoId, memoEditForm);
    		
    		MemoEditDTO memoEditDTO = memoService.getMemoEditDTO(projectId, memoId);
    		
            model.addAttribute("memoEditForm",memoEditForm);
            model.addAttribute("memoEditDTO",memoEditDTO);
            return "redirect:/projects/" + projectId + "/memos/" + memoId + "/edit";
    }
}
