package com.shao.eduservice.client.impl;

import com.shao.commonutils.Response;
import com.shao.eduservice.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {

    @Override
    public Response getUserMemberVo(String userId) {
        return Response.error().message("Feign调用失败，根据用户ID获取用户信息异常。");
    }
}
