package com.wx.app.service.impl;/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wx.app.entity.LoginUser;
import com.wx.app.entity.User;
import com.wx.app.mapper.MenuMapper;
import com.wx.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *@description TODO
 *@author zzhi
 *@createDate 2022/3/1
 *@version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userManager;

    @Autowired
    private MenuMapper menuManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userManager.selectOne(queryWrapper);


        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO 权限查询
        List<String> list = menuManager.selectPermsByUserId(user.getId());
        //封装为UserDetails
        return new LoginUser(user,list);
    }
}
