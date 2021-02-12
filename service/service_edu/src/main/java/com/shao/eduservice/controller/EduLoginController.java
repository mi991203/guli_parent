package com.shao.eduservice.controller;

import com.shao.eduservice.entity.dto.LoginDto;
import com.shao.commonutils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录管理")
@RestController
@RequestMapping("/edu-service/edu-login")
@CrossOrigin
public class EduLoginController {

    @ApiOperation("登录用户校验")
    @PostMapping("login")
    public Response login(@RequestBody LoginDto loginDto) {
        if (!ObjectUtils.isEmpty(loginDto)) {
            if ("admin".equals(loginDto.getUsername()) &&  "admin".equals(loginDto.getPassword())) {
                return Response.success().data("token", "login success");
            }else {
                return Response.error().data("token", "username or password error");
            }
        }
        return Response.error().data("token", "request body should not be null");
    }

    @ApiOperation("登录用户信息返回")
    @GetMapping("info")
    public Response info(String token) {
        //TODO 校验Token
        return Response.success().data("roles", "administrator").data("name", "ShaoHong").data("avatar", "http://localhost:8001/avatar02.png");
    }
}
