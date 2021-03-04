package com.shao.userservice.mapper;

import com.shao.userservice.entity.UserMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
