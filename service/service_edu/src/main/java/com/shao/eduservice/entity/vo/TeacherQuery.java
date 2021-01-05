package com.shao.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherQuery {
    @ApiModelProperty("教师名称，模糊查询")
    private String name;
    @ApiModelProperty("讲师级别")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
