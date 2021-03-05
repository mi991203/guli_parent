package com.shao.eduservice.client;

import com.shao.commonutils.Response;
import com.shao.eduservice.client.impl.UserClientImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user", fallback = UserClientImpl.class)
public interface UserClient {
    @GetMapping("/user-service/user/getUserMemberVo/{userId}")
    Response getUserMemberVo(@ApiParam(name = "userId", value = "用户ID") @PathVariable("userId") String userId);
}
