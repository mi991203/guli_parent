package com.shao.servicestatistics.client;

import com.shao.commonutils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-user")
public interface UcenterClient {
    @GetMapping("/user-service/user/count-register/{day}")
    Response countRegister(@PathVariable String day);
}
