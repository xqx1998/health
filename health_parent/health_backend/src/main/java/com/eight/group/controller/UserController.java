package com.eight.group.controller;

import com.eight.group.pojo.User;
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
    /**
     * 用户登录请求
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public User login(@RequestBody User user){
        System.out.println("user = " + user);
        return user;
    }
}
