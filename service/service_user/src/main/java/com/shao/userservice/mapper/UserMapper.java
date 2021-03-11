package com.shao.userservice.mapper;

import com.shao.userservice.entity.UserMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-27
 */
@Mapper
public interface UserMapper extends BaseMapper<UserMember> {

    /**
     * 某一个日期中注册人数
     * @param day 日期
     * @return 注册人数
     */
    @Select("SELECT COUNT(*) FROM ucenter_member WHERE DATA(gmt_create) = #{day} ")
    Integer countRegisterByDay(String day);
}
