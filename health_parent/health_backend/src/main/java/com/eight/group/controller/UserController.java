package com.eight.group.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.eight.group.constant.MessageConstant;
import com.eight.group.entity.Result;
import com.xqx.eight.group.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：xingquanxiang createTime：2019/11/3 19:53
 * description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/getUsername")
    public Result getUsername(){
        // 当spring security完成用户认证后，会将信息保存到框架提供的上下文对象
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null){
            System.out.println(user.getUsername());
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        }
        return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }
}