package com.shao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.client.VodClient;
import com.shao.eduservice.entity.EduVideo;
import com.shao.eduservice.mapper.EduVideoMapper;
import com.shao.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        List<EduVideo> eduVideos = baseMapper.selectList(new QueryWrapper<EduVideo>().eq("course_id", courseId).select("video_source_id"));
        List<String> batchList = eduVideos.stream().map(EduVideo::getVideoSourceId).filter(e -> !StringUtils.isEmpty(e)).collect(Collectors.toList());
        if (batchList.size() != 0) {
            vodClient.deleteBatch(batchList);
        }
        baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id", courseId));
    }
}
