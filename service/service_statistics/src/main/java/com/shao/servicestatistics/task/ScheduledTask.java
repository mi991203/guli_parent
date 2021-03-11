package com.shao.servicestatistics.task;

import com.shao.servicestatistics.service.StatisticsDailyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTask {
    @Resource
    private StatisticsDailyService dailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        log.info("{}执行了", Instant.now());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss");
        dailyService.createStatisticsByDay(LocalDateTime.now().minusDays(-1).format(dateTimeFormatter));
    }

}
