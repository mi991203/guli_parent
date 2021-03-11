package com.shao.servicestatistics.service;

import com.shao.servicestatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-03-11
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    /**
     * 通过日期创建统计信息
     * @param day 日期
     */
    void createStatisticsByDay(String day);

    /**
     * 通过起止日期和类型查询
     * @param begin 起始日期
     * @param end 截止日期
     * @param type 类型
     * @return Map数据
     */
    Map<String, Object> getChartData(String begin, String end, String type);
}
