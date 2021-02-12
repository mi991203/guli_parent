package com.shao.cmsservice.service;

import com.shao.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-05
 */
public interface CrmBannerService extends IService<CrmBanner> {
    /**
     * 获取更具ID排序前二的banner
     * @return 首页banner表集合
     */
    List<CrmBanner> getAllBanner();

}
