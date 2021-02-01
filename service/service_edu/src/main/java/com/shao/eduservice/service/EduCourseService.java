package com.shao.eduservice.service;

import com.shao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shao.eduservice.entity.dto.CourseQueryDto;
import com.shao.eduservice.entity.vo.CourseInfoVo;
import com.shao.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-06
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     * 
     * @param courseInfoVo 课程信息VO实体类
     * @return 课程Id
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程ID查询课程基本信息
     * @param courseId 课程Id
     * @return 课程信息VO实体类
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo 课程信息VO实体类
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程确认信息
     * @param id 课程Id
     * @return 课程信息概要
     */
    CoursePublishVo publishCourseInfo(String id);

    /**
     * 删除课程
     * @param courseId 课程ID
     */
    void removeCourse(String courseId);

    /**
     * 根据条件查询课程表
     * @param courseQueryDto 查询条件
     * @return 查询结果
     */
    List<EduCourse> getCourseInfoByCondition(CourseQueryDto courseQueryDto);
}
