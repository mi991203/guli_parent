package com.shao.eduservice.controller;


import com.shao.eduservice.entity.subject.OneSubject;
import com.shao.commonutils.Response;
import com.shao.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-05
 */
@Api(tags = "课程科目")
@RestController
@RequestMapping("/edu-service/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("添加课程科目")
    @PostMapping("addSubject")
    public Response addSubject(@RequestParam("file") MultipartFile multipartFile) {
        eduSubjectService.saveSubject(multipartFile, eduSubjectService);
        return Response.success();
    }

    //课程分类列表（树形）
    @ApiOperation("获取全部的课程科目信息（JSON格式）")
    @GetMapping("getAllSubject")
    public Response getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Response.success().data("list",list);
    }

}

