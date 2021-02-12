package com.shao.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.shao.commonutils.Response;
import com.shao.servicevod.utils.AlyPropertyConstants;
import com.shao.servicevod.service.VodService;
import com.shao.servicebase.exceptionhandler.GuliException;
import com.shao.servicevod.utils.InitVodClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/edu-vod/video")
@CrossOrigin
public class VodController {
    @Resource
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("upload-aly-video")
    public Response uploadAlyVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return Response.success().data("videoId",videoId);
    }

    // 根据视频ID删除阿里云视频
    @DeleteMapping("remove-aly-video/{id}")
    public Response removeAlyVideo(@PathVariable String id) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(AlyPropertyConstants.accessKeyId, AlyPropertyConstants.accessKeySecret);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return Response.success();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public Response deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Response.success();
    }
}
