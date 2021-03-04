package com.shao.cmsservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.cmsservice.entity.CrmBanner;
import com.shao.cmsservice.service.CrmBannerService;
import com.shao.commonutils.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cms-service/banner-front")
@CrossOrigin
public class BannerFrontController {
    @Resource
    private CrmBannerService bannerService;

    @ApiOperation("查询id降序排列的前两条Banner")
    @GetMapping("get-all-banner")
    public Response getAllBanner() {
        List<CrmBanner> allBanner = bannerService.getAllBanner();
        return Response.success().data("list", allBanner);
    }
}
