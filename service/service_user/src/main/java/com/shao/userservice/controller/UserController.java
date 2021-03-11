package com.shao.userservice.controller;


import com.alibaba.fastjson.JSON;
import com.shao.commonutils.JwtUtils;
import com.shao.commonutils.Response;
import com.shao.commonutils.vo.UserMemberVo;
import com.shao.userservice.entity.UserMember;
import com.shao.userservice.entity.vo.LoginVo;
import com.shao.userservice.entity.vo.RegisterVo;
import com.shao.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-27
 */
@Api("用户管理")
@RestController
@RequestMapping("/user-service/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    @ApiOperation("用户登录")
    public Response loginUser(@RequestBody LoginVo loginVo) {
        String token = userService.login(loginVo);
        return Response.success().data("token", token);
    }

    @PostMapping("register")
    @ApiOperation("用户注册")
    public Response registerUser(@RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return Response.success();
    }

    @GetMapping("/auth/getLoginInfo")
    @ApiOperation("根据token信息获取到用户信息")
    public Response getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UserMember userMember = userService.getLoginVoByMemberId(memberId);
        return Response.success().data("item", userMember);
    }

    @GetMapping("getUserMemberVo/{userId}")
    public Response getUserMemberVo(@ApiParam(name = "userId", value = "用户ID") @PathVariable("userId") String userId) {
        log.info("被调用");
        UserMember userMember = userService.getById(userId);
        UserMemberVo userMemberVo = new UserMemberVo();
        BeanUtils.copyProperties(userMember, userMemberVo);
        return Response.success().data("userMemberVo", userMemberVo);
    }

    @GetMapping("count-register/{day}")
    public Response countRegister(@PathVariable String day) {
        return Response.success().data("countRegister", userService.countRegisterByDay(day));
    }
}

