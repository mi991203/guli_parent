package com.shao.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlyPropertyConstants {
    public static String accessKeyId;
    public static String accessKeySecret;

    public AlyPropertyConstants(@Value("${aliyun.vod.accessKeyId}") String accessKeyId, @Value("${aliyun.vod.accessKeySecret}") String accessKeySecret) {
        AlyPropertyConstants.accessKeyId = accessKeyId;
        AlyPropertyConstants.accessKeySecret = accessKeySecret;
    }
}
