package com.shao.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String label;
    private List<TwoSubject> children;
}
