package com.shao.eduservice.client.impl;

import com.shao.commonutils.Response;
import com.shao.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientFallback implements VodClient {
    @Override
    public Response removeAlyVideo(String id) {
        return Response.error().message("删除视频出错");
    }

    @Override
    public Response deleteBatch(List<String> videoIdList) {
        return Response.error().message("批量删除视频出错");
    }
}
