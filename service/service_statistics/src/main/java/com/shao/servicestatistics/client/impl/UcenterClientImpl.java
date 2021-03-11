package com.shao.servicestatistics.client.impl;

import com.shao.commonutils.Response;
import com.shao.servicestatistics.client.UcenterClient;

public class UcenterClientImpl implements UcenterClient {
    @Override
    public Response countRegister(String day) {
        return Response.error().message("某日注册人口数量统计接口请求失败");
    }
}
