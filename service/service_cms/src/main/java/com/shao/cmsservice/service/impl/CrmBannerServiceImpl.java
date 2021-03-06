package com.shao.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.cmsservice.entity.CrmBanner;
import com.shao.cmsservice.mapper.CrmBannerMapper;
import com.shao.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-05
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Resource
    private CrmBannerMapper crmBannerMapper;

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> allBanner = crmBannerMapper.selectList(new QueryWrapper<CrmBanner>().orderByDesc("id").last("limit 2"));
        return allBanner;
    }
}
