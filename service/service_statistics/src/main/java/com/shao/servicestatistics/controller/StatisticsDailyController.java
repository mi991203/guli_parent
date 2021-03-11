package com.shao.servicestatistics.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.shao.commonutils.Response;
import com.shao.servicestatistics.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author ShaoHong
 * @since 2021-03-11
 */
@CrossOrigin
@RestController
@RequestMapping("/service-statistics/statistics-daily")
public class StatisticsDailyController {
    @Resource
    private StatisticsDailyService dailyService;
    @PostMapping("{day}")
    public Response createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return Response.success();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public Response showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return Response.success().data(map);
    }

}

