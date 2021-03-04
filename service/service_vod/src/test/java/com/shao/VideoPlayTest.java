package com.shao;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VideoPlayTest {

    @Test
    public void getPlayUrl() {
        DefaultAcsClient client = initVodClient("LTAI4GKH9Qp67TYzELdK8mLg", "u6M8SpOxBnsD8E2Pwe5IUQuOMPCOf0");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getPlayInfo(client);
            System.out.println(response.getPlayAuth());
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    private static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        String regionId = "cn-shanghai"; // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    private static GetVideoPlayAuthResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("6c2c63a609bf41fcbbee5082f8636ee2");
        return client.getAcsResponse(request);
    }
}
