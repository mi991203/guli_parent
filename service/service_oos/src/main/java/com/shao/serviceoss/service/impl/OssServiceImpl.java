package com.shao.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.shao.serviceoss.service.OssService;
import com.shao.serviceoss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtils.END_POINT, ConstantPropertiesUtils.KEY_ID,
                        ConstantPropertiesUtils.KEY_SECRET);
        // 上传的文件名使用UUID随机数+上传文件名+当前日期yyyy-MM-dd的字符串拼接
        String fileName = new DateTime().toString("yyyy/MM/dd") + UUID.randomUUID().toString().replaceAll("-", "")
                        + multipartFile.getOriginalFilename();
        try {
            ossClient.putObject(ConstantPropertiesUtils.BUCKET_NAME, fileName, multipartFile.getInputStream());
            return "https://"+ConstantPropertiesUtils.BUCKET_NAME+"."+ConstantPropertiesUtils.END_POINT+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
