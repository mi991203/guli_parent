package com.shao.eduservice.entity.vo;

import lombok.Data;

/**
 * 课程信息概要
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    //只用于显示
    private String price;
}

