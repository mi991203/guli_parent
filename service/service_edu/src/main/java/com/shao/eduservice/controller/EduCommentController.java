package com.shao.eduservice.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.commonutils.JwtUtils;
import com.shao.commonutils.Response;
import com.shao.commonutils.vo.UserMemberVo;
import com.shao.eduservice.client.UserClient;
import com.shao.eduservice.entity.EduComment;
import com.shao.eduservice.service.EduCommentService;
import com.shao.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-03-04
 */
@Api(tags = "课程品论管理API")
@RestController
@RequestMapping("/edu-service/edu-comment")
public class EduCommentController {
    @Resource
    private EduCommentService eduCommentService;
    @Resource
    private UserClient userClient;

    @GetMapping("{current}/{limit}")
    public Response getCommentPage(@ApiParam(name = "page", value = "当前页", required = true) @PathVariable Long current,
                    @ApiParam(name = "limit", value = "每页页数", required = true) @PathVariable Long limit,
                    @ApiParam(name = "userId", value = "用户ID", required = false) String courseId) {
        Page<EduComment> page = new Page<>(current, limit);
        eduCommentService.page(page, new QueryWrapper<EduComment>().eq("course_id", courseId));
        Map<String, Object> map = new HashMap<>();
        map.put("items", page.getRecords());
        map.put("current", page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("total", page.getTotal());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return Response.success().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public Response save(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return Response.error().code(20002).message("请先登录");
        }
        comment.setMemberId(memberId);
        Response response = userClient.getUserMemberVo(memberId);
        if (response.getCode() != 20000) {
            throw new GuliException(20002, "Feign调用失败。");
        }
        Object linkedHashMapObj = response.getData().get("userMemberVo");
        UserMemberVo userMemberVo = JSON.parseObject(JSON.toJSONString(linkedHashMapObj), UserMemberVo.class);
        comment.setNickname(userMemberVo.getNickname());
        comment.setAvatar(userMemberVo.getAvatar());
        eduCommentService.save(comment);
        return Response.success();
    }

}

