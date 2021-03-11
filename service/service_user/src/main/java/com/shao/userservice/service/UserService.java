package com.shao.userservice.service;

import com.shao.userservice.entity.UserMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shao.userservice.entity.vo.LoginVo;
import com.shao.userservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-27
 */
public interface UserService extends IService<UserMember> {

    /**
     * 用户登录
     * @param member 登陆对象
     * @return token
     */
    String login(LoginVo member);

    /**
     * 用户注册
     * @param registerVo 注册对象
     */
    void register(RegisterVo registerVo);

    /**
     * 根据memberId获取到登录对象信息
     * @param memberId 用户ID
     * @return 用户登录对象
     */
    UserMember getLoginVoByMemberId(String memberId);

    /**
     * 查询某一天中注册人数
     * @param day 日期
     * @return 注册人数
     */
    Integer countRegisterByDay(String day);
}
