package com.shao.eduservice.controller;


import com.shao.commonutils.Response;
import com.shao.eduservice.client.VodClient;
import com.shao.eduservice.entity.EduVideo;
import com.shao.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    private EduVideoService eduVideoService;
    @Resource
    private VodClient vodClient;

    //添加小节
    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public Response addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Response.success();
    }

    //删除小节
    @ApiOperation("删除小节")
    @DeleteMapping("delete/{id}")
    public Response deleteVideo(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        if (!StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            vodClient.removeAlyVideo(eduVideo.getVideoSourceId());
        }
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

