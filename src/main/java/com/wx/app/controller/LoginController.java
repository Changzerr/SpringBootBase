package com.wx.app.controller;/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.wx.app.entity.User;
import com.wx.app.service.LoginService;
import com.wx.app.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@description TODO
 *@author zzhi
 *@createDate 2022/3/1
 *@version 1.0
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("user/login")
    public Result login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("user/register")
    public Result register(@RequestBody User user){
        return loginService.register(user);
    }

    @RequestMapping("user/logout")
    public Result logout(){
        return loginService.logout();
    }
}
