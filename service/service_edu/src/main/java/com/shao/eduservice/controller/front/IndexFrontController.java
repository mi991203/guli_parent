package com.shao.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.EduTeacher;
import com.shao.eduservice.service.EduCourseService;
import com.shao.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("edu-service/index-front")
public class IndexFrontController {
    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("获取前八热门讲师和前四热门课程")
    @Cacheable(value = "index", key = "'limitRecord'")
    @GetMapping("index")
    public Response index() {
        // 获取到前8条讲师的记录
        List<EduTeacher> eduTeachers = eduTeacherService.list(new QueryWrapper<EduTeacher>().orderByDesc("id").last("limit 8"));
        // 获取前四条课程的记录
        List<EduCourse> eduCourses = eduCourseService.list(new QueryWrapper<EduCourse>().orderByDesc("id").last("limit 4"));
        return Response.success().data("teacherList", eduTeachers).data("courseList", eduCourses);
    }
}
