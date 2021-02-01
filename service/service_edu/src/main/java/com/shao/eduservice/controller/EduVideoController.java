package com.shao.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.shao.commonutils.Response;
import com.shao.eduservice.entity.EduVideo;
import com.shao.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-07
 */
@Api(tags = "课程小节")
@RestController
@RequestMapping("/edu-service/edu-video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public Response addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Response.success();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @ApiOperation("删除小节")
    @DeleteMapping("delete/{id}")
    public Response deleteVideo(@PathVariable String id) {
        eduVideoService.removeById(id);
        return Response.success();
    }

    // 根据小节ID查询小节
    @ApiOperation("根据小节ID查询小节")
    @GetMapping("/queryVideoById/{id}")
    public Response queryVideoById(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return Response.success().data("video", eduVideo);
    }

    // 根据videoId对表进行更新
    @ApiOperation("根据videoId对表进行更新")
    @PostMapping("updateVideoById")
    public Response updateVideoById(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return Response.success();
    }


}

