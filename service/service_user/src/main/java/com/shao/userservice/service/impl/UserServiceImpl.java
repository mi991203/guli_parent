package com.shao.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.commonutils.JwtUtils;
import com.shao.servicebase.exceptionhandler.GuliException;
import com.shao.userservice.entity.UserMember;
import com.shao.userservice.entity.vo.LoginVo;
import com.shao.userservice.entity.vo.RegisterVo;
import com.shao.userservice.mapper.UserMapper;
import com.shao.userservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shao.userservice.utils.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.management.Query;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserMember> implements UserService {

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "登录失败");
        }
        // 判断手机号是否已经注册过
        QueryWrapper<UserMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UserMember userMember = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(userMember)) {
            throw new GuliException(20001, "手机号未被注册");
        }
        // 判断密码是否正确
        if (!MD5.encrypt(password).equals(userMember.getPassword())) {
            throw new GuliException(20001, "密码不正确");
        }
        // 判断用户是否被禁用
        if (userMember.getIsDisabled()) {
            throw new GuliException(20001, "用户被禁用");
        }
        // 登录成功
        return JwtUtils.getJwtToken(userMember.getId(), userMember.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        // 昵称
        String nickname = registerVo.getNickname();
        // 手机号
        String mobile = registerVo.getMobile();
        // 密码
        String password = registerVo.getPassword();
        // 判断输入的值是否为空
        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "输入的值不能为空");
        }
        // 判断这个手机号是否被注册
        Integer mobileRegisterCount = baseMapper.selectCount(new QueryWrapper<UserMember>().eq("mobile", mobile));
        if (mobileRegisterCount != 0) {
            throw new GuliException(20001, "这个手机号已被注册");
        }
        UserMember userMember = new UserMember();
        userMember.setNickname(nickname).setMobile(mobile).setPassword(MD5.encrypt(password)).setAvatar(
                        "https://guli-shao-1010.oss-cn-beijing.aliyuncs.com/2020-12-05bb94e2442e7048f1a332cfa5fa7dbe09avatar02%20%282%29.png");
        this.save(userMember);
    }

    @Override
    public UserMember getLoginVoByMemberId(String memberId) {
        UserMember userMember = baseMapper.selectById(memberId);
        if (ObjectUtils.isEmpty(userMember)) {
            throw new GuliException(20001, "error");
        }
        /*LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(userMember, loginVo);*/
        return userMember;
    }
}
