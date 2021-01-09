package com.shao.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduChapter;
import com.shao.eduservice.entity.chapter.ChapterVo;
import com.shao.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
@Api(tags = "课程章节")
@RestController
@RequestMapping("/edu-service/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("根据课程Id查询章节(小结)信息")
    @GetMapping("getChapterVideo/{courseId}")
    public Response getChapterVideoByCourseId(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return Response.success().data("list", list);
    }

    //添加章节
    @ApiOperation("添加章节")
    @PostMapping("addChapter")
    public Response addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return Response.success();
    }

    //根据章节id查询
    @ApiOperation("根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public Response getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Response.success().data("chapter",eduChapter);
    }

    //修改章节
    @ApiOperation("修改章节")
    @PostMapping("updateChapter")
    public Response updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return Response.success();
    }

    //删除的方法
    @ApiOperation("删除的方法")
    @DeleteMapping("deleteChapter/{chapterId}")
    public Response deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag) {
            return Response.success();
        } else {
            return Response.error();
        }

    }

}

