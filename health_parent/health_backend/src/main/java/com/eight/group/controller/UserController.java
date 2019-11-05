package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.pojo.TUser;
import com.xqx.eight.group.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：xingquanxiang createTime：2019/11/3 19:53
 * description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;
    /**
     * 用户登录请求
     * @param TUser
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody TUser TUser){
        System.out.println("user = " + TUser);
        return userService.login(TUser);
    }
}
