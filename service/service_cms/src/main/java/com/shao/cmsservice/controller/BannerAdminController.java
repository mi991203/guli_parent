package com.shao.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.cmsservice.entity.CrmBanner;
import com.shao.cmsservice.service.CrmBannerService;
import com.shao.commonutils.Response;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-05
 */
@RestController
@RequestMapping("/cms-service/banner-admin")
public class BannerAdminController {
    @Resource
    private CrmBannerService crmBannerService;

    @ApiOperation("分页查询")
    @GetMapping("pageBanner/{current}/{limit}")
    public Response pageBanner(@PathVariable Long current, @PathVariable Long limit) {
        Page<CrmBanner> page = new Page<>(current, limit);
        crmBannerService.page(page, null);
        return Response.success().data("items", page.getCurrent()).data("total", page.getTotal());
    }

    @ApiOperation("添加Banner")
    @PostMapping("add-banner")
    public Response addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return Response.success();
    }

    @ApiOperation("修改Banner")
    @PutMapping("update")
    public Response updateById(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return Response.success();
    }

    @ApiOperation("删除Banner")
    @DeleteMapping("remove/{id}")
    public Response remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Response.success();
    }

}

