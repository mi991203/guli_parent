package com.shao.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduTeacher;
import com.shao.eduservice.entity.vo.TeacherQuery;
import com.shao.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2020-11-22
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/edu-service/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 查询所有的数据
    @ApiOperation("查询所有讲师信息")
    @GetMapping("findAll")
    public Response findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return Response.success().data("items", list);
    }

    // 逻辑删除讲师的方法
    @ApiOperation("根据ID逻辑删除数据库中的记录")
    @DeleteMapping("{id}")
    public Response removeTeacher(
                    @ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id) {
        return eduTeacherService.removeById(id) ? Response.success() : Response.error();
    }

    // 3 分页查询讲师的方法
    @ApiOperation("分页查询讲师信息表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Response pageListTeacher(
                    @ApiParam(name = "current", value = "当前记录", required = true) @PathVariable("current") long current,
                    @ApiParam(name = "limit", value = "每页容量", required = true) @PathVariable("limit") long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.ge("id", 18);
        eduTeacherService.page(pageTeacher, eduTeacherQueryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Response.success().data("total", total).data("items", records);
    }

    @ApiOperation("条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Response pageTeacherCondition(
                    @ApiParam(name = "current", value = "当前记录", required = true) @PathVariable("current") long current,
                    @ApiParam(name = "limit", value = "每页容量", required = true) @PathVariable("limit") long limit,
                    @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建一个page对象
        Page<EduTeacher> page = new Page<>(current, limit);
        // 构建查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        if (!ObjectUtils.isEmpty(teacherQuery)) {
            if (!StringUtils.isEmpty(teacherQuery.getName())) {
                wrapper.like("name", teacherQuery.getName());
            }
            if (!ObjectUtils.isEmpty(teacherQuery.getLevel())) {
                wrapper.eq("level", teacherQuery.getLevel());
            }
            if (!ObjectUtils.isEmpty(teacherQuery.getBegin())) {
                wrapper.ge("gmt_create", teacherQuery.getBegin());
            }
            if (!ObjectUtils.isEmpty(teacherQuery.getEnd())) {
                wrapper.le("gmt_modified", teacherQuery.getEnd());
            }
        }
        // 调用方法实现分页查询的效果
        eduTeacherService.page(page, wrapper);
        return Response.success().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    @ApiOperation("添加教师信息")
    @PostMapping("addTeacher")
    public Response addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher) ? Response.success() : Response.error();
    }

    @ApiOperation("根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public Response getTeacher(@ApiParam("讲师id") @PathVariable long id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Response.success().data("teacher", eduTeacher);
    }

    @ApiOperation("讲师记录修改")
    @PutMapping("updateTeacher")
    public Response updateTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.updateById(eduTeacher) ? Response.success() : Response.error();
    }

}

