package com.shao.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.dto.CourseQueryDto;
import com.shao.eduservice.entity.vo.CourseInfoVo;
import com.shao.eduservice.entity.vo.CoursePublishVo;
import com.shao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-06
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/edu-service/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation("课程列表")
    @GetMapping
    public Response getCourseList(CourseQueryDto courseQueryDto) {
        return Response.success().data("list",courseService.getCourseInfoByCondition(courseQueryDto));
    }

    // 添加课程基本信息的方法
    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public Response addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return Response.success().data("courseId", id);
    }

    // 根据课程ID查询课程基本信息
    @ApiOperation("根据课程ID查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public Response getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Response.success().data("courseInfoVo", courseInfoVo);
    }

    //修改课程信息
    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public Response updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Response.success();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public Response getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Response.success().data("publishCourse",coursePublishVo);
    }

    // 修改课程状态
    @ApiOperation("课程最终发布")
    @PutMapping("publishCourse/{id}")
    public Response publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id).setStatus("Normal");
        courseService.updateById(eduCourse);
        return Response.success();
    }

    //删除课程
    @ApiOperation("删除课程")
    @DeleteMapping("deleteCourse/{courseId}")
    public Response deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Response.success();
    }
}

