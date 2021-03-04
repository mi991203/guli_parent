package com.shao.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.shao.commonutils.Response;
import com.shao.servicevod.utils.AlyPropertyConstants;
import com.shao.servicevod.service.VodService;
import com.shao.servicebase.exceptionhandler.GuliException;
import com.shao.servicevod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Api("阿里云视频点播")
@RestController
@RequestMapping("/edu-vod/video")
@CrossOrigin
@Slf4j
public class VodController {
    @Resource
    private VodService vodService;

    // 上传视频到阿里云
    @PostMapping("upload-aly-video")
    public Response uploadAlyVideo(MultipartFile file) {
        // 返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return Response.success().data("videoId", videoId);
    }

    // 根据视频ID删除阿里云视频
    @DeleteMapping("remove-aly-video/{id}")
    public Response removeAlyVideo(@PathVariable String id) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(AlyPropertyConstants.accessKeyId,
                            AlyPropertyConstants.accessKeySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return Response.success();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }

    // 删除多个阿里云视频的方法
    // 参数多个视频id List videoIdList
    @DeleteMapping("delete-batch")
    public Response deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Response.success();
    }

    @GetMapping("get-play-auth/{videoId}")
    @ApiOperation("根据视频ID获取到凭证")
    public Response getPlayAuth(@ApiParam(name = "videoId", value = "课程ID") @PathVariable String videoId) {
        // initClient
        String accessKeyId = AlyPropertyConstants.accessKeyId;
        String accessKeySecret = AlyPropertyConstants.accessKeySecret;
        DefaultAcsClient client =
                        new DefaultAcsClient(DefaultProfile.getProfile("cn-shanghai", accessKeyId, accessKeySecret));
        // new request
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // get response
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new GuliException(20001, "根据VideoId获取凭证失败");
        }
//        log.info("获取视频凭证成功，凭证为：{}", response.getPlayAuth());
        return Response.success().message("获取凭证成功").data("playAuth", response.getPlayAuth());
    }
}
