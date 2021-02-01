package com.shao.eduservice.mapper;

import com.shao.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shao.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-06
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 通过课程Id获取课程信息概要
     * @param courseId 课程ID
     * @return 课程信息概要
     */
    public CoursePublishVo getPublishCourseInfo(String courseId);

}
