package com.shao.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.shao.service.VodService;
import com.shao.servicebase.exceptionhandler.GuliException;
import com.shao.utils.AlyPropertyConstants;
import com.shao.utils.InitVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Autowired
    AlyPropertyConstants alyPropertyConstants;

    @Override
    public String uploadVideoAly(MultipartFile file) {
        String videoId = null;
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream is = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(AlyPropertyConstants.accessKeyId,
                            AlyPropertyConstants.accessKeySecret, title, fileName, is);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            videoId = response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;
    }

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(AlyPropertyConstants.accessKeyId, AlyPropertyConstants.accessKeySecret);
            // 创建请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 将List的值转换成1,2,3的格式
            String ids = StringUtils.join(videoIdList.toArray(), ",");
            request.setVideoIds(ids);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }
}
