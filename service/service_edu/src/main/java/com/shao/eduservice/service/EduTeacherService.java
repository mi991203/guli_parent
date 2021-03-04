package com.shao.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shao.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2020-11-22
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 讲师前台分页
     * @param pagePara 分页对象
     * @return 分页后结果
     */
    public Map<String, Object> pageListWeb(Page<EduTeacher> pagePara);

}
