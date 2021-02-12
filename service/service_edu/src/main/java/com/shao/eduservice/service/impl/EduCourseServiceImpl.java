package com.shao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.entity.EduCourseDescription;
import com.shao.eduservice.entity.vo.CourseInfoVo;
import com.shao.eduservice.mapper.EduCourseMapper;
import com.shao.eduservice.service.EduChapterService;
import com.shao.eduservice.service.EduCourseDescriptionService;
import com.shao.eduservice.service.EduCourseService;
import com.shao.eduservice.service.EduVideoService;
import com.shao.eduservice.entity.EduCourse;
import com.shao.eduservice.entity.dto.CourseQueryDto;
import com.shao.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shao.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

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

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述（因为课程ID也是课程描述的ID）
        eduCourseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0) { //失败返回
            throw new GuliException(20001,"删除失败");
        }
    }

    @Override
    public List<EduCourse> getCourseInfoByCondition(CourseQueryDto courseQueryDto) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQueryDto.getTitle())){
            wrapper.like("title", courseQueryDto.getTitle());
        }
        if (!StringUtils.isEmpty(courseQueryDto.getStatus())) {
            wrapper.eq("status", courseQueryDto.getStatus());
        }
        return baseMapper.selectList(wrapper);
    }
}
