package com.shao.eduservice.client;

import com.shao.commonutils.Response;
import com.shao.eduservice.client.impl.VodClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodClientFallback.class)
public interface VodClient {
    // @PathVariable一定要指定参数名称
    @DeleteMapping("/edu-vod/video/remove-aly-video/{id}")
    Response removeAlyVideo(@PathVariable("id") String id);

    @DeleteMapping("/edu-vod/video/delete-batch")
    Response deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
