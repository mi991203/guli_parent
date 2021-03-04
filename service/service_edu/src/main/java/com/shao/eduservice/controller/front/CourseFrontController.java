package com.shao.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.vo.CourseQueryVo;
import com.shao.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "前台课程相关")
@RestController
@RequestMapping("edu-service/front-course")
@CrossOrigin
public class CourseFrontController {
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public Response pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return  Response.success().data(map);
    }
}
