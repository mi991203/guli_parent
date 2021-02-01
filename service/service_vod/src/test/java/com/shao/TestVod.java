package com.shao;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

public class TestVod {
    public static void main(String[] args) throws ClientException {
        uploadVideo();
    }

    public static void uploadVideo() {
        String title = "上传测试名";
        String fileName = "C:/Users/Kobe/Desktop/6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest("LTAI4GKH9Qp67TYzELdK8mLg",
                        "u6M8SpOxBnsD8E2Pwe5IUQuOMPCOf0", title, fileName);
        // 设置文件分片的大小
        request.setPartSize(1024 * 1024L);
        // 设置处理文件上传的线程数
        request.setTaskNum(1);
        // 设置断点续传
        request.setEnableCheckpoint(true);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            System.out.println("VideoId=" + response.getVideoId());
        }else {
            System.out.println("VideoId=" + response.getVideoId());
            System.out.println("ErrorCode=" + response.getCode());
        }
    }

    public static void getPlayAuthUrl() throws ClientException {
        // 获取初始化对象
        DefaultAcsClient client =
                        InitObject.initVodClient("LTAI4GKH9Qp67TYzELdK8mLg", "u6M8SpOxBnsD8E2Pwe5IUQuOMPCOf0");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("385be44e10254f8bb8b851b657624075");
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }

    public static void getPlayUrl() throws ClientException {
        // 获取初始化对象
        DefaultAcsClient client =
                        InitObject.initVodClient("LTAI4GKH9Qp67TYzELdK8mLg", "u6M8SpOxBnsD8E2Pwe5IUQuOMPCOf0");
        // 得到request 和 response 对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 调用初始化对象中相应方法传入request对象得到赋值后的response对象
        request.setVideoId("385be44e10254f8bb8b851b657624075");
        GetPlayInfoResponse response = client.getAcsResponse(request);
        // 操作response对象
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        playInfoList.forEach(e -> System.out.println(e.getPlayURL()));
        System.out.println("Title====" + response.getVideoBase().getTitle());
    }
}
