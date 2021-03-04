package com.shao.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.EduTeacher;
import com.shao.eduservice.service.EduCourseService;
import com.shao.eduservice.service.EduTeacherService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api("有关于讲师的前台API接口")
@RestController
@RequestMapping("edu-service/front-teacher")
public class TeacherFrontController {
    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation("分页讲师列表（前台）")
    @GetMapping("{page}/{limit}")
    public Response pageList(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                    @ApiParam(name = "limit", value = "每页页数", required = true) @PathVariable Long limit) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);
        return Response.success().data(map);
    }

    @ApiOperation("根据讲师ID获取到讲师信息和课程信息")
    @GetMapping("/{teacherId}")
    public Response getInfoByTeacherId(
                    @ApiParam(name = "teacherId", value = "讲师ID", required = true) @PathVariable String teacherId) {
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        List<EduCourse> courseList = eduCourseService
                        .list(new QueryWrapper<EduCourse>().eq("teacher_id", teacherId).orderByDesc("gmt_modified"));
        return Response.success().data("teacher", teacher).data("courseList", courseList);
    }


}
