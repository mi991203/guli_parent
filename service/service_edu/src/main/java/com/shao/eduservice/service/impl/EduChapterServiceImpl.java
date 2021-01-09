package com.shao.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.entity.EduChapter;
import com.shao.eduservice.entity.EduVideo;
import com.shao.eduservice.entity.chapter.ChapterVo;
import com.shao.eduservice.entity.chapter.VideoVo;
import com.shao.eduservice.mapper.EduChapterMapper;
import com.shao.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shao.eduservice.service.EduVideoService;
import com.shao.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        // 首先根据课程ID分别查询章节柯小结数据库，查询两次数据库，然后得到数据后在拼接得到相应的数据并返回
        List<EduChapter> eduChapterList = baseMapper.selectList(new QueryWrapper<EduChapter>().eq("course_id", courseId));
        List<EduVideo> eduVideoList = eduVideoService.list(new QueryWrapper<EduVideo>().eq("course_id", courseId));
        // 封装结果返回
        List<ChapterVo> resultList = new ArrayList<>();
        eduChapterList.forEach(eduChapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            List<VideoVo> children = new ArrayList<>();
            eduVideoList.forEach(eduVideo -> {
                if (eduChapter.getId().equals(eduVideo.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    children.add(videoVo);
                }
            });
            chapterVo.setChildren(children);
            resultList.add(chapterVo);
        });
        return resultList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id 查询小节表，如果查询数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        //判断
        if(count >0) {//查询出小节，不进行删除
            throw new GuliException(20001,"不能删除");
        } else { //不能查询数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            //成功  1>0   0>0
            return result>0;
        }
    }
}
