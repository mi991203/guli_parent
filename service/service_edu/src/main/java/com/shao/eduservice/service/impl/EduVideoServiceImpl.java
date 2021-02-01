package com.shao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.entity.EduVideo;
import com.shao.eduservice.mapper.EduVideoMapper;
import com.shao.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void removeVideoByCourseId(String courseId) {
        baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id", courseId));
    }
}
