package com.shao.eduservice.service;

import com.shao.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shao.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程Id获取到课程的章节信息
     * @param courseId 课程Id
     * @return 章节信息
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节
     * @param chapterId 章节ID
     * @return 删除是否成功
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程ID删除章节
     * @param courseId 课程ID
     */
    void removeChapterByCourseId(String courseId);
}
