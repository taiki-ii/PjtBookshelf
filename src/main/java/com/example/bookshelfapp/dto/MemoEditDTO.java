package com.example.bookshelfapp.dto;

import java.util.List;

import com.example.bookshelfapp.entity.Memo;
import com.example.bookshelfapp.entity.Project;

import lombok.Data;

@Data
public class MemoEditDTO {

    private Project project;
    private List<Memo> allMemos;
    private Memo selectedMemo;

    public MemoEditDTO
                (Project project,
                 List<Memo> allMemos,
                 Memo selectedMemo){
            this.project = project;
            this.allMemos = allMemos;
            this.selectedMemo = selectedMemo;
        }
}
