package com.shao.eduservice.service;

import com.shao.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程Id删除小节
     * @param courseId 课程Id
     */
    void removeVideoByCourseId(String courseId);
}
