package com.shao.eduservice.service;

import com.shao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shao.eduservice.entity.vo.CourseInfoVo;

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
}
