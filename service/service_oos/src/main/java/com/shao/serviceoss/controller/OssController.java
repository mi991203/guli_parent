package com.shao.serviceoss.controller;

import com.shao.commonutils.Response;
import com.shao.serviceoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/edu-oss/file-oss")
@ComponentScan("com.shao.servicebase")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    // 上传头像的方法
    @PostMapping
    public Response uploadOssFile(@RequestParam(value = "file") MultipartFile multipartFile) {
        String url = ossService.uploadFileAvatar(multipartFile);
        return !StringUtils.isEmpty(url) ? Response.success().data("url", url)
                        : Response.error().data("message", "文件上传失败");
    }
}
