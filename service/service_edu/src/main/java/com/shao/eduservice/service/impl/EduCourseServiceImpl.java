package com.shao.eduservice.service.impl;

import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.EduCourseDescription;
import com.shao.eduservice.entity.vo.CourseInfoVo;
import com.shao.eduservice.mapper.EduCourseMapper;
import com.shao.eduservice.service.EduCourseDescriptionService;
import com.shao.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shao.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 根据传入的课程信息Vo对象抽取相关值保存到课程表中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int count = baseMapper.insert(eduCourse);
        if (count == 0) {
            throw new GuliException(20003, "向课程表中插入记录失败");
        }

        // 根据传入的courseInfoVo对象中抽取课程简介存入到edu_course_description表中
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = this.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        // 查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new GuliException(20001,"修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(description);
    }
}
