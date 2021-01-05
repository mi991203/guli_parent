package com.shao.serviceoss.utils;


import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConstantPropertiesUtils {
    private final Environment environment;
    /**
     * 机房名
     */
    public static String END_POINT;
    /**
     * ID
     */
    public static String KEY_ID;
    /**
     * KEY的密码
     */
    public static String KEY_SECRET;
    /**
     * 桶名
     */
    public static String BUCKET_NAME;

    public ConstantPropertiesUtils(Environment environment) {
        this.environment = environment;
        END_POINT = environment.getProperty("aliyun.oos.file.endpoint");
        KEY_ID = environment.getProperty("aliyun.oos.file.keyid");
        KEY_SECRET = environment.getProperty("aliyun.oos.file.keysecret");
        BUCKET_NAME = environment.getProperty("aliyun.oos.file.bucketname");
    }


}
